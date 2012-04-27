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
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null)  
		{
			String theWord=strLine;
			if(((strLine = br.readLine()) != null))
			{
				LinkedList<String> translation=new LinkedList<String>(Arrays.asList(strLine.split(" ")));
				if(((strLine = br.readLine()) != null))
				{
					String pos=strLine;
					if(((strLine = br.readLine()) != null))
					{
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
		s.toLowerCase();
		if(esp2eng)
		{
			return espToEngDict.get(s);
		}
		return engToEspDict.get(s);
	}

}
