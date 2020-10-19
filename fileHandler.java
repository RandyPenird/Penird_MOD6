package Application;
import java.net.*;
import java.io.*;
import java.util.*;
public class fileHandler 
{
//URL variable stores the address for the provided link for this assignment
	private static URL website;
//Scanner captures the inputstream from the URL and collects data with carrier
	private static Scanner scanner;
	public static ArrayList<String> finalarray = new ArrayList<String>();
	public static ArrayList<Integer> finalcount = new ArrayList<Integer>();
	
//the following methods opens and catches any errors if the URL isn't reachable
	public static void openFile()
	{
		try
		{
//sets website URL to the provided URL
		website = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
//passes the input stream from URL to scanner
		scanner = new Scanner(website.openStream());
		}
		catch(Exception e)
		{
			System.out.println("Error connecting to File");
		}
	}
	
//The following method reads the file, indexes words in the poem and counts the unique words and their frequency
	public static void countWords()
	{
//line string stores the working data from the scanner
		String line;
//This array list will store all words in the poem
		ArrayList<String> wordarray = new ArrayList<String>();
//this do loop will scrub the file until the EOF is reached
		do
		{
//this segment ignores HTML code before the unique text beginning the poem
			line = scanner.nextLine();
//Once the title is found the key is added to the arraylist to begin saving data
			if(line.equals("The Raven"))
			{
//this sets all the text to uppercase for aesthetic choices
				line = line.toUpperCase();
//splits words using the " " delimiter
				String[] words = line.split(" ");
//adds the values in the workbench array 'words' to the arraylist
				wordarray.addAll(0, Arrays.asList(words));
//following do loop reads and saves the words to the arraylist until the end of the poem is reached, using a unique key
				do
				{
				line = scanner.nextLine();
					if(!"".equals(line) && line.charAt(0) != '<')
					{
//This is a filter tree of ReplaceAll using Regex to remove any HTML tags and rogue punctuation that isnt uniform in the document.
						line = line.replaceAll("\\<.*?\\>", "").replaceAll("[^\\w ]", "").replaceAll("mdash", " ").replaceAll("\\s+", " ").toUpperCase();
						words = line.split(" ");
						wordarray.addAll(Arrays.asList(words));		
					}
				}while(!"<BR><BR><BR><BR>".equals(line));
			}
		}while(scanner.hasNext());
		scanner.close();
		
//The following finds unique occurrences in array and saves them to hashSet
		Set<String> uniquewords = new HashSet<String>(wordarray);
//Treemap to save the unique words and organize them in ascending order
		TreeMap<Integer,String> finallist = new TreeMap<Integer, String>(Collections.reverseOrder());
//counts the frequency of the Hashlist words and displays those numbers on screen
		System.out.println("Top 20 words, and their frequency:");
		//counts the frequency of the unique words and places them in TreeSet in Key, String pairs
		for(String x : uniquewords)
		{
			int y = Collections.frequency(wordarray, x);
			finalcount.add(y);
			finallist.put(y, x);
		}
//Set of keys and values to use iterator for print to console
		Set finalset = finallist.entrySet();
//Iterator to control how many to print according to proceeding for loop
		Iterator iterator = finalset.iterator();
		iterator.next();
		
		finalarray.addAll(finallist.values());
		  
	}
}
