#include <stdio.h>
#include <regex.h>

int main(int argc, char **argv)
{
	struct {
		const char	*input;
		int		 expect;
	} tests[] = {
		/* should match */
		{ "state (: q0", 1 },
		{ "state(: q0",  1 },
		{ "state(:0s",  1 },

		/* should not match */
		{ "#state (:q0",  0 },
		{ "state( q0",    0 },
		{ "# state (:q0", 0 },
	};
	int i;
	regex_t start_state;
	const char *pattern = "^[ \\t]*(state)\\s*\\(:.*$";

	if (regcomp(&start_state, pattern, REG_EXTENDED)) {
		fprintf(stderr, "%s: bad pattern: '%s'\n", argv[0], pattern);
		return 1;
	}

	for (i = 0; i < sizeof(tests)/sizeof(tests[0]); i++) {
		int status = regexec(&start_state, tests[i].input, 0, NULL, 0);

		printf("%s: %s (%s)\n", tests[i].input,
                            status == 0 ? "match" : "no match",
                            !status == !!tests[i].expect
                              ? "PASS" : "FAIL");
	}

	regfree(&start_state);

	return 0;
}
