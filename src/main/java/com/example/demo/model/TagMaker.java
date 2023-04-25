package com.example.demo.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.stemmer.snowball.SnowballStemmer;

public class TagMaker {
	public ArrayList<String> getTags(String choice) throws IOException {
		
		String summery = "";
		
		SnowballStemmer st = new SnowballStemmer(SnowballStemmer.ALGORITHM.ENGLISH);
		
		/*
		System.out.println("THIS IS THE STEMMING PART");
		System.out.println(st.stem("words"));
		*/
		
		if(choice.equals("a"))
		{
			summery = "Teacher, XYZ Elementary School, Anytown, USA, 20XX - Present\r\n"
					+ "- Plan and implement engaging lesson plans for a diverse group of students in grades 1-5\r\n"
					+ "- Incorporate technology and hands-on learning activities to keep students engaged\r\n"
					+ "- Collaborate with colleagues to develop and implement school-wide initiatives\r\n"
					+ "- Communicate regularly with parents about student progress and behavior\r\n" + "\r\n"
					+ "English Teacher, Anytown High School, Anytown, USA, 20XX - 20XX\r\n"
					+ "- Taught a variety of literature, writing, and language arts courses to high school students\r\n"
					+ "- Developed and implemented engaging lesson plans that aligned with state standards\r\n"
					+ "- Provided individualized instruction and support to struggling students\r\n"
					+ "- Chaperoned school events and participated in extracurricular activities with students\r\n" + "\r\n"
					+ "Skills:\r\n" + "- Proficient in Microsoft Office and Google Suite\r\n"
					+ "- Familiar with classroom technology, including interactive whiteboards and educational apps\r\n"
					+ "- Strong communication skills, both written and verbal\r\n"
					+ "- Ability to collaborate with colleagues and work as part of a team\r\n"
					+ "- Passion for creating a positive learning environment and helping students succeed";
		}
		
		else if(choice.equals("b"))
		{
			summery = "Develop and implement engaging and effective lesson plans aligned with state standards\r\n"
					+ "Utilize technology tools to enhance student learning and engagement\r\n"
					+ "Incorporate differentiated instruction to meet the needs of diverse learners\r\n"
					+ "Manage classroom behavior using positive reinforcement and behavior modification strategies\r\n"
					+ "Collaborate with colleagues and parents to support student success\r\n"
					+ "Student Teacher\r\n"
					+ "ABC Elementary School, Anytown, USA\r\n"
					+ "January 2018 - May 2018\r\n"
					+ "\r\n"
					+ "Observed and assisted lead teacher in the development and implementation of lesson plans\r\n"
					+ "Planned and taught lessons in math, reading, and social studies\r\n"
					+ "Conducted small group instruction and provided individualized support to struggling learners\r\n"
					+ "Utilized educational technology tools to enhance student engagement\r\n"
					+ "Collaborated with lead teacher to plan and execute parent-teacher conferences\r\n"
					+ "Certifications:\r\n"
					+ "\r\n"
					+ "State Teaching Certification, Anytown, USA (2018)\r\n"
					+ "CPR and First Aid Certification (current)";
		}
		
		else if (choice.equals("c"))
		{
			summery = "Develop and implement rigorous and engaging lesson plans aligned with state standards\r\n"
					+ "Utilize technology tools to enhance student learning and engagement\r\n"
					+ "Incorporate student-centered learning strategies to foster critical thinking and problem-solving skills\r\n"
					+ "Manage classroom behavior using positive reinforcement and restorative justice practices\r\n"
					+ "Collaborate with colleagues and parents to support student success\r\n"
					+ "Student Teacher\r\n"
					+ "Smalltown Elementary School, Smalltown, USA\r\n"
					+ "January 2018 - May 2018\r\n"
					+ "\r\n"
					+ "Assisted lead teacher in the development and implementation of lesson plans\r\n"
					+ "Planned and taught lessons in math, reading, and social studies\r\n"
					+ "Conducted small group instruction and provided individualized support to struggling learners\r\n"
					+ "Utilized educational technology tools to enhance student engagement\r\n"
					+ "Collaborated with lead teacher to plan and execute parent-teacher conferences\r\n"
					+ "Certifications:\r\n"
					+ "\r\n"
					+ "State Teaching Certification, Smalltown, USA (2018)\r\n"
					+ "Google for Education Certified Educator (current)";
		}
		
		else
		{
			summery = choice;
		}

		// This removes all punctuation and all words with numbers and numbers
		// themselves.
		summery = summery.replaceAll("\\p{Punct}", "");
		summery = summery.replaceAll("\\b\\w*\\d+\\w*\\b", "");

		// This is a list of stop words. After that they will all be removed from the
		// input
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

		String stopWordsPattern = String.join("|", stopWords);
		Pattern pattern = Pattern.compile("\\b(?:" + stopWordsPattern + ")\\b\\s*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(summery);
		summery = matcher.replaceAll("");

		// Loading the Tokenizer model
		InputStream inputStream = new FileInputStream("C:/OpenNLP_models/en-token.bin");
		TokenizerModel tokenModel = new TokenizerModel(inputStream);

		// Instantiating the TokenizerME class
		TokenizerME tokenizer = new TokenizerME(tokenModel);

		// Tokenizing the given raw text
		String tokens[] = tokenizer.tokenize(summery);
		
		ArrayList<String> tok = new ArrayList<String>();
		String b = "";
		for (String a : tokens) {
			b = (String) st.stem(a); 
			tok.add(b.toLowerCase());
		}
		
		
		
		return tok;
	}
}