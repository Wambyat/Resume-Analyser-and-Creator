package com.example.demo;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.Loader;


import java.util.*;

public class predictivekeyboard {
    public List<String> lista;
    public void getSuggestions(String input) throws IOException 
    {
                
        SuggestionEngine obj1 = new SuggestionEngine();
        
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
            obj1.insert(words[i]);
        }
        
        
        int numPredictions = 3;
        
        
        this.lista = obj1.getSuggestions(input,numPredictions);
        
        
    } 
    
}