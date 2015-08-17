__author__ = 'zzt'
import zlib
import sys

USAGE = 'Usage: cws2fws COMPRESSED_SWF_FILE'
CWS_PREFIX = b'CWS'
FWS_PREFIX = b'FWS'


def cws2fws(filepath):
    try:
        fin = open(filepath, 'rb')
    except IOError:
        raise Exception('Error: Could not open source SWF file {}'.format(filepath))

    # is case sensitive, bytes must match ASCII 0x43 0x57 0x53 ('CWS' )
    if fin.read(3) != CWS_PREFIX:
        raise Exception('Flash file is not compressed(CWS) or header is invalid.')

    # reads last bytes of header
    header = fin.read(5)

    # I observed that the CWS file length(bytes 5 to 8, little endian 32 bit integer)
    # does not match that of the compressed
    # data. by decompressing and checking again I see that the length header field
    # refers to the zlib uncompressed stream + 8 bytes (header length). therefore
    # to finish our decompression all we have to do is substitute the first 3
    # byte signature for FWS and concatenate the rest of the header. works a dandy.
    # - JMAF
    try:
        fout = open(filepath + '.swf', 'wb')
    except IOError:
        raise Exception('Error: Could not open target SWF file {}'.format(filepath) + '.swf')
    else:
        decompress = zlib.decompress(fin.read())
        fout.write(FWS_PREFIX + header + decompress)


if __name__ == '__main__':
    from os.path import split as s

    if not sys.argv[1:]:
        print(USAGE)
        sys.exit(1)

    for filepath in sys.argv[1:]:
        try:
            cws2fws(filepath)
        except:
            errtype, errstr = sys.exc_info()[:2]
            print(s(filepath)[1], '->', s(filepath)[1] + '.swf:', errstr)
        else:
            print(s(filepath)[1], '->', s(filepath)[1] + '.swf:', 'OK')
