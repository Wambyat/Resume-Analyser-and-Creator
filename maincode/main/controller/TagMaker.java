package maincode.main.controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import opennlp.tools.stemmer.snowball.SnowballStemmer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class TagMaker {
    public ArrayList<String> getTags(String choice) throws IOException {

        String summery = choice;

        SnowballStemmer st = new SnowballStemmer(SnowballStemmer.ALGORITHM.ENGLISH);

        // This removes all punctuation and all words with numbers and all only numbers.
        summery = summery.replaceAll("\\p{Punct}", "");
        summery = summery.replaceAll("\\b\\w*\\d+\\w*\\b", "");

        // TODO: Pls SQL
        String[] stopWords = new String[] { "a", "able", "about", "above", "according", "accordingly", "across",
                "actually", "after", "afterwards", "again", "against", "all", "allow", "allows", "almost", "alone",
                "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another",
                "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear",
                "appreciate", "appropriate", "are", "aren't", "around", "as", "aside", "ask", "asking", "associated",
                "at", "available", "away", "awfully", "b", "back", "be", "became", "because", "become", "becomes",
                "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides",
                "best", "better", "between", "beyond", "both", "brief", "but", "by", "c", "came", "can", "cannot",
                "cant", "can't", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come",
                "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains",
                "corresponding", "could", "couldn't", "course", "currently", "d", "dear", "definitely", "described",
                "despite", "did", "didn't", "different", "do", "does", "doesn't", "doing", "done", "don't", "down",
                "downwards", "during", "e", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough",
                "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything",
                "everywhere", "ex", "exactly", "example", "except", "f", "far", "few", "fifth", "first", "five",
                "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further",
                "furthermore", "g", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got",
                "gotten", "greetings", "h", "had", "hadn't", "happens", "hardly", "has", "hasn't", "have", "haven't",
                "having", "he", "he'd", "he'll", "hello", "help", "hence", "her", "here", "hereafter", "hereby",
                "herein", "here's", "hereupon", "hers", "herself", "he's", "hi", "high", "him", "himself", "his",
                "hither", "hopefully", "how", "howbeit", "however", "how's", "i", "i'd", "ie", "if", "ignored", "i'll",
                "i'm", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner",
                "insofar", "instead", "into", "inward", "is", "isn't", "it", "its", "it's", "itself", "i've", "j",
                "just", "k", "keep", "keeps", "kept", "know", "known", "knows", "l", "last", "lately", "later",
                "latter", "latterly", "least", "less", "lest", "let", "let's", "like", "liked", "likely", "little",
                "long", "look", "looking", "looks", "ltd", "m", "made", "mainly", "make", "many", "may", "maybe", "me",
                "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "mustn't",
                "my", "myself", "n", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither",
                "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor",
                "normally", "not", "nothing", "novel", "now", "nowhere", "o", "obviously", "of", "off", "often", "oh",
                "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise",
                "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "p", "particular",
                "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably",
                "provides", "put", "q", "que", "quite", "qv", "r", "rather", "rd", "re", "really", "reasonably",
                "regarding", "regardless", "regards", "relatively", "respectively", "right", "s", "said", "same", "saw",
                "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems",
                "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall",
                "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "since", "six", "so", "some",
                "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon",
                "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "t", "take",
                "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "that's", "the",
                "their", "theirs", "them", "themselves", "then", "thence", "there", "thereafter", "thereby",
                "therefore", "therein", "theres", "there's", "thereupon", "these", "they", "they'd", "they'll",
                "they're", "they've", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three",
                "through", "throughout", "thru", "thus", "tis", "to", "together", "too", "took", "toward", "towards",
                "tried", "tries", "truly", "try", "trying", "twas", "twice", "two", "u", "un", "under", "unfortunately",
                "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using",
                "usually", "uucp", "v", "value", "various", "very", "via", "viz", "vs", "w", "want", "wants", "was",
                "wasn't", "way", "we", "we'd", "welcome", "well", "we'll", "went", "were", "we're", "weren't", "we've",
                "what", "whatever", "what's", "when", "whence", "whenever", "when's", "where", "whereafter", "whereas",
                "whereby", "wherein", "where's", "whereupon", "wherever", "whether", "which", "while", "whither", "who",
                "whoever", "whole", "whom", "who's", "whose", "why", "why's", "will", "willing", "wish", "with",
                "within", "without", "wonder", "won't", "would", "wouldn't", "x", "y", "yes", "yet", "you", "you'd",
                "you'll", "your", "you're", "yours", "yourself", "yourselves", "you've", "z", "zero" };

        // This removes all the stop-words.
        String stopWordsPattern = String.join("|", stopWords);
        Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(summery);
        summery = matcher.replaceAll("");

        // Loading the Tokenizer model
        InputStream inputStream = new FileInputStream("./maincode/main/controller/en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStream);
        TokenizerME tokenizer = new TokenizerME(tokenModel);

        // Tokenizing the given text
        String tokens[] = tokenizer.tokenize(summery);

        // Taking the above tokens and stemming them (This is removing post-fixes like
        // "ing" , "s" etc.
        ArrayList<String> tok = new ArrayList<String>();
        String b = "";
        for (String a : tokens) {
            b = (String) st.stem(a);
            tok.add(b.toLowerCase());
        }

        return tok;
    }
    public static void main(String args[]) throws Exception
    {
        System.out.println("Howdy");
        TagMaker t = new TagMaker();
        System.out.println("Input  String: Existing testing stuff uwus lmaos ig??? I dunno");
        System.out.print("Output String: " + t.getTags("Existing testing stuff uwus lmaos ig??? I dunno"));
    }
}
