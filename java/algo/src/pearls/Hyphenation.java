package pearls;

/**
 * Created by zzt on 5/1/16.
 * <p>
 * <h3>Problem:</h3>
 * <li>rules for words end in 'c' is defined in testCase/word_hyphenation</li>
 * <li>rules must be applied in order in file, i.e. the previous rule have high priority</li>
 *
 * <h3>Solution</h3>
 * <ui>A reversed mapping trie of all rules: suffix => priority</ui>
 * <li>if the word can reach the end: finish</li>
 * <li>if the word can't reach end:find deepest node it can reach</li>
 * <h3>Explanation</h3>
 * <li>deeper mean higher priority: for a previous rule must not be the suffix of latter one</li>
 * <li>e.g. an-tic, n-tic; h-nic, n-ic; -clic, l-ic</li>
 */
public class Hyphenation {

}
