package main.controller;
import java.util.*;

public class SuggestionEngine {
	
	class TrieNode {
		
	    char value;																		// alphabet value associated to this node (a-z)
	    boolean isEnd;																	// boolean value to determine whether this is the end of a word
	    int count;																		// number of times this node has been visited through
	    HashMap<Character, TrieNode> children;											// an array of Nodes that are the children of that current Node	

	    public TrieNode() { }
	 
	    public TrieNode(char c){
	        this.value = c;
	        this.count = 0;
	        this.isEnd = false;
	        this.children = new HashMap<Character, TrieNode>();
	    }
	    
	    public char getChar()
	    {
	    	return this.value;
	    }
	    	    	    	    
	}
	
    private TrieNode root;
 
    public SuggestionEngine() {
        root = new TrieNode(' ');

		String[] words = { "a", "able", "about", "above", "according", "accordingly", "across",
                "actually", "after", "afterwards", "again", "against", "all", "allow", "allows", "almost", "alone",
                "along", "already", "also", "although", "always","Achieved","Analyzed","Assisted","Awarded","Adapted",
                "Authored","Assembled","Audited","Administered","Aligned", "am", "among", "amongst", "an", "and", "another",
                "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear",
                "appreciate", "appropriate", "are", "aren't", "around", "as", "aside", "ask", "asking", "associated",
                "at", "available", "away", "awfully", "back", "be", "became", "because", "become", "becomes",
                "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides",
                "best", "better", "between", "beyond", "both", "brief", "but", "by","Budgeted","Built","Business acumen",
                "Brainstormed","Boosted","Best practices","Balanced","Benchmarked","Briefed","Branded","c", "came", "can", "cannot",
                "cant", "can't", "cause", "causes", "certain", "certainly", "changes", "clearly", "com", "come",
                "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains",
                "corresponding", "could", "couldn't", "course", "currently","Collaborated","Coordinated","Communicated",
                "Customer service","Conceptualized","Constructed","Critiqued","Conducted","Created","Complied","d", "dear", 
                "definitely", "described","Developed","Designed","Debugged","Deployed","Determined","Delivered","Demonstrated",
                "Diagnosed","Distributed","despite", "did", "didn't", "different", "do", "does", "doesn't", "doing", "done", "don't", "down",
                "downwards", "during", "e", "each", "edu", "eight", "either", "else", "elsewhere", "enough",
                "entirely", "especially", "etc", "even", "ever", "every", "everybody", "everyone", "everything",
                "everywhere", "exactly", "example", "except","Excelled","experience","Evaluated","Executed","Expanded",
                "Enabled","Elevated","Engineered","Established","Estimated","Educated","f", "far", "few", "fifth", "first", "five",
                "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further",
                "furthermore","Facilitated","Formulated","Fixed","Fulfilled","Focused","Finalized","Forecasted", "Fostered",
                "Functioned", "Founded", "g", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got",
                "gotten", "greetings","Generated","Guided","Grew","Governed","Granted","Gained","Gathered","Gave",
                "Grouped","Gained" ,"h", "had", "hadn't", "happens", "hardly", "has", "hasn't", "have", "haven't",
                "having", "he", "he'd", "he'll", "hello", "help", "hence", "her", "here", "hereafter", "hereby",
                "herein", "here's", "hereupon", "hers", "herself", "he's", "hi", "high", "him", "himself", "his",
                "hither", "hopefully", "how", "howbeit", "however", "how's","Headed","Handled","Helped","Hired",
                "Honed", "Harmonized","Highlighted","Hosted","Handled","Healed" ,"i", "i'd", "ie", "if", "ignored", "i'll",
                "i'm", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner",
                "insofar", "instead", "into", "inward", "is", "isn't", "it", "its", "it's", "itself", "i've","Improved","Innovated",
                "Implemented","Influenced","Initiated","Inspected","Integrated","Instructed","Invented","Identified","j",
                "just","Joined","Juggled","Judged","Justified","Jolted","Joined forces","Jettisoned","Joined hands",
                "Journeyed","Judiciously","k", "keep", "keeps", "kept", "know", "known", "knows","Kept",
                "Keynoted","Kick-started","Kitted","Kindled","Keenness","Kinetic","Kid-gloves",
                "Kill shot", "l", "last", "lately", "later","latter", "latterly", "least", "less", "lest", "let", "let's", "like", "liked", "likely", "little",
                "long", "look", "looking", "looks", "ltd","Led","Liaised","Launched","Lectured","Leveraged","Linked",
                "Logged","Lifted","Listened","Localized","Located", "m", "made", "mainly", "make", "many", "may", "maybe", "me",
                "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must","my", "myself", "Managed",
                "Maximized","Market analysis","Mentored","Measured","Modified","Motivated","Mobilized",
                "Monitored","Maintained","n", "name", "namely", "near", "nearly", "necessary", "need", "needs", "neither",
                "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor",
                "normally", "not", "nothing", "novel", "now", "nowhere","Negotiated","Networked",
                "Navigated","Nurtured","Nominated","Notified","Noted","Normalized","Named",
                "Neutralized", "o", "obviously", "of", "off", "often", "oh",
                "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise",
                "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own","Organized",
                "Optimized","Operated","Ordered","Orchestrated","Observed","Obtained","Outlined","Offered","Overhauled", "p", "particular",
                "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably",
                "provides", "put", "Planned","Predicted","Processed","Prepared","Presented","Prioritized","Produced","Problem-solved","Proposed",
                "Promoted","q", "que", "quite", "qv","Qualified","Quantified","Queried","Questioned","Quickened","Quit","Quizzed","Qualified experience","Quality assurance",
                "Quality control", "r", "rather", "really", "reasonably","Reached","Recommended","Redesigned","Reduced",
                "Resolved","Reported","regarding", "regardless", "regards", "relatively", "respectively", "right", "s", "said", "same", "saw",
                "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems",
                "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall",
                "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "since", "six", "so", "some",
                "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon",
                "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "t", "take",
                "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "that's", "the",
                "their", "theirs", "them", "themselves", "then", "thence", "there", "thereafter", "thereby",
                "therefore", "therein", "theres", "there's", "thereupon", "these", "they", "they'd", "they'll",
                "they're", "they've", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three",
                "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards",
                "tried", "tries", "truly", "try", "trying", "twas", "twice", "two", "u", "under", "unfortunately",
                "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using",
                "usually", "uucp", "v", "value", "various", "very", "via", "viz", "vs", "w", "want", "wants", "was",
                "wasn't", "way", "we", "we'd", "welcome", "well", "we'll", "went", "were", "we're", "weren't", "we've",
                "what", "whatever", "what's", "when", "whence", "whenever", "when's", "where", "whereafter", "whereas",
                "whereby", "wherein", "where's", "whereupon", "wherever", "whether", "which", "while", "whither", "who",
                "whoever", "whole", "whom", "who's", "whose", "why", "why's", "will", "willing", "wish", "with",
                "within", "without", "wonder", "won't", "would", "wouldn't", "x", "y", "yes", "yet", "you", "you'd",
                "you'll", "your", "you're", "yours", "yourself", "yourselves", "you've", "z", "zero","Zeroed in","Zipped","Zoned",
                "Zoomed","Zested","Zapped","Zigzagged","Zonked","Zested for success","Advertised", "Addressed", "Arbitrated", "Authored", "Collaborated", "Composed", "Conferred", "Contacted", "Convinced", "Debated", "Discussed", "Edited", "Enlisted", "Expressed", "Furnished", "Informed", "Influenced", "Interpreted", "Interviewed", "Judged", "Listened", "Mediated", "Negotiated", "Outlined", "Persuaded", "Promoted", "Publicized", "Recruited", "Reinforced", "Resolved", "Solicited", "Summarized", "Translated",
                "Acted", "Adapted", "Combined", "Conceptualized", "Created", "Designed", "Developed", "Directed", "Drew", "Established", "Fashioned", "Founded", "Formulated", "Generated", "Illustrated", "Initiated", "Instituted", "Integrated", "Introduced", "Invented", "Modeled", "Originated", "Performed", "Photographed", "Planned", "Produced", "Revitalized", "Shaped",
                "Advocated", "Aided", "Answered", "Assessed", "Clarified", "Collaborated", "Counseled", "Cooperated", "Demonstrated", "Educated", "Ensured", "Expedited", "Facilitated", "Furthered", "Guided", "Helped", "Intervened", "Led", "Mentored", "Prevented", "Referred", "Rehabilitated", "Represented", "Simplified", "Supported",
                "Achieved", "Administered", "Analyzed", "Assigned", "Approved", "Chaired", "Contracted", "Consolidated", "Controlled", "Coordinated", "Delegated", "Developed", "Drafted", "Directed", "Eliminated", "Evaluated", "Enhanced", "Executed", "Expanded", "Handled", "Hired", "Implemented", "Improved", "Increased", "Inspected", "Merged",
                "Approved", "Arranged", "Coordinated", "Categorized", "Classified", "Collected", "Corrected", "Distributed", "Executed", "Generated", "Filed", "Implemented", "Incorporated", "Logged", "Monitored", "Operated", "Organized", "Processed", "Purchased", "Registered", "Responded", "Routed", "Screened", "Supplied", "Systematized", "Tabulated", "Validated",
                "Applied", "Assembled", "Built", "Calculated", "Coded", "Computed", "Constructed", "Converted", "Designed", "Debugged", "Determined", "Devised", "Engineered", "Fortified", "Maintained", "Overhauled", "Programmed", "Regulated", "Repaired", "Restored", "Solved", "Specialized", "Standardized" };

    
        
    
        
        for(int i = 0; i < words.length; i++)
        {
            this.insert(words[i]);
        }
        
    }
 
    public void insert(String word) 
    {
    	word = word.toLowerCase();
    	
        HashMap<Character, TrieNode> children = root.children;
        
        TrieNode current;      
        char c;
        
        for(int i = 0; i<word.length(); i++)
        {
            c = word.charAt(i);
     
            if(children.containsKey(c))
            {
                    current = children.get(c);
            }
            else
            {
                current = new TrieNode(c);
                children.put(c, current);
            }
 
            if(i == word.length() - 1)
            {
                current.isEnd = true;
                current.count++;
            }         
            children = current.children;
        }
    }
 
    public boolean find(String word) 
    {
    	word = word.toLowerCase();
    	
        TrieNode current = findNode(word);
 
        if(current != null && current.isEnd)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
  
    public TrieNode findNode(String word)
    {
    	
        HashMap<Character, TrieNode> children = root.children;     
        TrieNode current = null;
        char c;
        
        for(int i = 0; i < word.length(); i++){
        	
        	c = word.charAt(i);
            
            if(children.containsKey(c))
            {
                current = children.get(c);
                children = current.children;
            }
            else
            {
                return null;
            }
        }
 
        return current;
    }
                
    public HashMap<String, Integer> sortByValueReverse(HashMap<String, Integer> hashMap)
    {    	
    	List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hashMap.entrySet());
    	
    	Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
    	{
    		public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
    		{
    			return (o2.getValue().compareTo(o1.getValue()));
    		}
    	});
    	
    	HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
    	
    	for(Map.Entry<String, Integer> aa : list)
    	{
    		temp.put(aa.getKey(), aa.getValue());
    	}
    	
    	return temp;
    }   
  
    public void printRootChildren() 
    {
    	System.out.println(root.children);
    }
        
    public void preOrderTraversalHelper(List<String> listOfWords, TrieNode current, String prefix)
    {
    	for(Map.Entry<Character, TrieNode> each : current.children.entrySet())
    	{
    		char temp = each.getKey();
    		preOrderTraversalHelper(listOfWords, each.getValue(), prefix + temp);
    		current = each.getValue();
    		if(current.count > 0)
    		{
    			listOfWords.add(prefix + temp);
    		}
    	}    	
    }
    
    public List<String> preOrderTraversal(String prefix)
    {
    	List<String> listOfWords = new ArrayList<String>();
    	
    	TrieNode lastCharNode = findNode(prefix);
    	
    	preOrderTraversalHelper(listOfWords, lastCharNode, prefix);
    	
    	return listOfWords;
    }

    public List<String> getSuggestions(String prefix, int n )
    {
        n =3;
    	if (prefix == null)
    	{
    		return new ArrayList<String>();
    	}
    	prefix = prefix.toLowerCase();
    	
    	List<String> listOfWords = preOrderTraversal(prefix);
    	
    	HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	
    	for(String temp : listOfWords)
    	{
    		counts.put(temp, findNode(temp).count);
    	}
    	
    	counts = sortByValueReverse(counts);
    	    	    	
    	List<String> topNWords = new ArrayList<String>();
    	
    	for(Map.Entry<String, Integer> entry : counts.entrySet())
    	{
    		if(n == 0)
    		{
    			break;
    		}
    		else
    		{
    			topNWords.add(entry.getKey());
    			n--;
    		}
    	}
    	   
    	return topNWords;
    }
}