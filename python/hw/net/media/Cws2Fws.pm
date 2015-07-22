package Plagger::Plugin::Filter::Cws2Fws;

require 5.6.0;

use strict;
use base qw( Plagger::Plugin );
use Compress::Zlib;

sub register {
    my($self, $context) = @_;
    $context->register_hook(
        $self,
        'update.entry.fixup' => \&filter,
    );
}

sub filter {
    my($self, $context, $args) = @_;

    my $entry = $args->{entry};

    my $enclosure = $entry->enclosure;
    if (!defined $enclosure) {
        $context->log( error => q{Can't find an enclosure.} );
        return;
    }

    eval { $enclosure->local_path };
    if ($@) {
        $context->log( error => q{Can't get local file.} );
        return;
    }

    # detect CWS
    my $local_path = $enclosure->local_path;
    if ((-s $local_path) < 8) {
	    return;
    }
    open(my $fh, $local_path) or return;
    my $header;
    read $fh, $header, 8;
    if ($header !~ /^CWS/) {
        close $fh;
        return;
    }

    # fix position
    if (length($header) > 8) {
	    $header = substr($header, 0, 8);
	    seek($fh, 8, 0);
    }

    # read body.
    local $/; # enable localized slurp mode
    my $buffer = <$fh>;
    close $fh;

    # output file.
    open(my $out, '> ' . $local_path) or return;  # overwrite!
    $header =~ s/^C/F/;
    $buffer = uncompress($buffer);
    print $out $header;
    print $out $buffer;
    close $out;

    $context->log( info => 'Complete Cws2Fws.' );
}

1;
__END__

=head1 NAME

Plagger::Plugin::Filter::Cws2Fws - convert swf file from CWS to FWS.

=head1 SYNOPSIS

  - module: Filter::Cws2Fws

=head1 DESCRIPTION

convert swf file from CWS to FWS.

=head1 CONFIG

  - module: Filter::Cws2Fws

=head1 AUTHOR

miya2000

=head1 SEE ALSO

L<Plagger>

=cut
