package annie;
import java.io.*;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;


public class MyDictionary {
	private HashMap<String, Word> engToEspDict= new HashMap<String, Word>();
	private HashMap<String, Word> espToEngDict= new HashMap<String, Word>();
	
	public MyDictionary(String file)
	{
		try {
			buildDictionary(file);
		} catch (IOException e) {
			// TODO some error case
		}
	}

	public void buildDictionary(String file) throws IOException
	{
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF8"));
		String strLine;
		while ((strLine = br.readLine()) != null)  
		{
			// skip comments and blank lines
			if (strLine.equals("") || strLine.startsWith("#")) {
				continue;
			}
			
			String theWord=strLine;
			if(((strLine = br.readLine()) != null))
			{
				// skip comments and blank lines
				while (strLine.equals("") || strLine.startsWith("#")) {
					strLine = br.readLine();
				}
				
				LinkedList<String> translation=new LinkedList<String>(Arrays.asList(strLine.split(", ")));
				if(((strLine = br.readLine()) != null))
				{
					// skip comments and blank lines
					while (strLine.equals("") || strLine.startsWith("#")) {
						strLine = br.readLine();
					}
					
					String pos=strLine;
					if(((strLine = br.readLine()) != null))
					{
						// skip comments and blank lines
						while (strLine.equals("") || strLine.startsWith("#")) {
							strLine = br.readLine();
						}
						
						String exSent=strLine;
						Word w=new Word(translation, pos, exSent);
						espToEngDict.put(theWord, w);
						for(String esp:translation)
						{
							LinkedList<String> t;
							if(engToEspDict.containsKey(esp))
							{
								t= engToEspDict.get(esp).getTranslations();
							}
							else
							{
								t= new LinkedList<String>();
							}
							t.add(theWord);
							engToEspDict.put(esp, new Word(t, pos, exSent));
						}
					}
				}
			}
		}
		in.close();
	}
	
	public Word getWord(String s, Boolean esp2eng)
	{
		if(s==null)
		{
			return null;
		}
		s=s.toLowerCase();
		if(esp2eng)
		{
			return espToEngDict.get(deinflect(s));
		}
		return engToEspDict.get(s);
	}
	
	public static String deinflect(String s) {
		String ret = null;
		
		// Check endings
		if (s.endsWith("jn")) {
			// plural accusatives
			ret = s.substring(0, s.length() - 2);
		} else if (s.endsWith("j") || s.endsWith("n") && (!s.equals("en") && !s.equals("sen") && !s.equals("kun"))) {
			// plurals and accusatives
			ret = s.substring(0, s.length() - 1);
		} else if ((s.endsWith("as") || s.endsWith("is") || s.endsWith("os") || s.endsWith("us")) && s.length() >= 2) {
			// finite verbs
			ret = s.substring(0, s.length() - 2) + "i";
		} else if (s.endsWith("u") && !s.equals("kiu") && !s.equals("tiu") && !s.equals("iu")
				&& !s.equals("ĉiu") && !s.equals("neniu")) {
			// imperatives; screen out correlatives
			ret = s.substring(0, s.length() - 1) + "i";
		} else {
			// otherwise, just return the input. 
			ret = s;
		}
		
		// special case to catch greetings...
		if(s.equals("saluton")||s.equals("nun"))
		{
			ret=s;
		}
		
		return ret;
	}

}
