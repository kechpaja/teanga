package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ELearning.Driver;

@SuppressWarnings("serial")
public class GUIFoundationPage extends JPanel{
	private Driver _driver;
	private String _helpPhrase;
	private int _help1, _help2, _topButtonLoc, _bottomButtonLoc, _buttonHeight;
	private boolean _dictRead;
	private Point _mainPanelLocation;
	private JPanel _forHelpBox, _mainPanel, _topPanel, _bottomPanel;
	private JButton _middleButton, _back, _help, _dictionary;
	private JLabel _un, _score;

	public GUIFoundationPage(Driver driver, boolean haveButtons){
		super(null);
		_driver = driver;
		_forHelpBox = this;
		_helpPhrase = " ";
		_help1 = -1;
		_help2 = -1;
		_mainPanel = new JPanel(null);
		_dictRead = true;

		_topButtonLoc = 4;
		_bottomButtonLoc = 5;
		_buttonHeight = 30;

		Point topPanelLocation = new Point(0,0);
		Point bottomPanelLocation = new Point(0,631);
		_mainPanelLocation = new Point(0,38);

		//Top Panel
		_topPanel = new JPanel(null);
		_topPanel.setBackground(new Color(50,50,50,255));
		_topPanel.setBorder(BorderFactory.createEmptyBorder());
		_topPanel.setSize(new Dimension(994,38));
		_topPanel.setLocation(topPanelLocation);

		if(haveButtons){
			_un = new JLabel(_driver.getPlayerStats().getUsername());
			_un.setFont(new Font("Cambria", Font.PLAIN, 20));
			_un.setForeground(Color.white);
			_un.setLocation(20, _topButtonLoc-3);
			_un.setSize(new Dimension(200,35));

			String scoreWord = "Punktoj tutaj: "+_driver.getPlayerStats().getPoints();
			_score = new JLabel(scoreWord);
			_score.setFont(new Font("Cambria", Font.PLAIN, 20));
			_score.setForeground(Color.white);
			FontMetrics metrics = _score.getFontMetrics(new Font("Cambria", Font.PLAIN, 20));
			int width = SwingUtilities.computeStringWidth(metrics, scoreWord);
			_score.setLocation((994-width)/2, _topButtonLoc-3);
			_score.setSize(new Dimension(width,35));
			
			_topPanel.add(_un);
			_topPanel.add(_score);
		} else{
			_un = null;
			_score = null;
		}

		BufferedImage backpic = null;
		try {
			backpic = ImageIO.read(new File("data/OtherPictures/backarrow.png"));
		} catch (IOException e){
			String errorMessage = "There was an error reading some of the files necessary \n to run ELearning. You may need to redownload the program.";
			JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Oh No!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		int type3 = BufferedImage.TYPE_INT_ARGB;
		BufferedImage dst3 = new BufferedImage(20, 20, type3);
		Graphics2D g3 = dst3.createGraphics();
		g3.drawImage(backpic, 0, 0, 20, 20, this);
		g3.dispose();
		ImageIcon newIcon3 = new ImageIcon(dst3);

		_back = new JButton("Reiru",newIcon3);
		_back.setSize(new Dimension(125, _buttonHeight));
		_back.setLocation(994-125-20,_topButtonLoc);

		_topPanel.add(_back);

		//Bottom Panel
		_bottomPanel = new JPanel(null);
		_bottomPanel.setBackground(new Color(50,50,50,255));
		_bottomPanel.setBorder(BorderFactory.createEmptyBorder());
		_bottomPanel.setSize(new Dimension(1000,38));
		_bottomPanel.setLocation(bottomPanelLocation);

		BufferedImage dictpic = null;
		try {
			dictpic = ImageIO.read(new File("data/OtherPictures/realdictionary.png"));
		} catch (IOException e){

		}
		int type = BufferedImage.TYPE_INT_ARGB;
		BufferedImage dst = new BufferedImage(27, 27, type);
		Graphics2D g1 = dst.createGraphics();
		g1.drawImage(dictpic, 0, 0, 27, 27, this);
		g1.dispose();
		ImageIcon newIcon = new ImageIcon(dst);

		BufferedImage helppic = null;
		try {
			helppic = ImageIO.read(new File("data/OtherPictures/QuestionMark.png"));
		} catch (IOException e){

		}
		int type2 = BufferedImage.TYPE_INT_ARGB;
		BufferedImage dst2 = new BufferedImage(23, 23, type2);
		Graphics2D g2 = dst2.createGraphics();
		g2.drawImage(helppic, 0, 0, 23, 23, this);
		g2.dispose();
		ImageIcon newIcon2 = new ImageIcon(dst2);

		_help = new JButton("Helpu",newIcon2);
		_help.setSize(new Dimension(125, _buttonHeight));
		_help.setLocation(20, _bottomButtonLoc);

		_dictionary = new JButton("Vortaro",newIcon);
		_dictionary.setSize(new Dimension(125, _buttonHeight));
		_dictionary.addActionListener(new DictionaryButtonListener());
		_dictionary.setLocation(994-125-20, _bottomButtonLoc);

		_middleButton = new JButton("MiddleButton");
		_middleButton.setVisible(false);

		_bottomPanel.add(_help);
		_bottomPanel.add(_middleButton);
		_bottomPanel.add(_dictionary);
	}

	public void hideEverything(){
		_help.setVisible(false);
		_dictionary.setVisible(false);
		_back.setVisible(false);
	}
	
	public void hideBottom(){
		_dictionary.setVisible(false);
		_help.setVisible(false);
	}

	public void setHelp(ActionListener actionL){
		_help.addActionListener(actionL);
	}
	
	public void setBack(ActionListener backListener, String text, boolean icon){
		if(!icon){
			_topPanel.remove(_back);
			_back = new JButton(text);
			_back.setSize(new Dimension(125, _buttonHeight));
			_back.setLocation(994-125-20,_topButtonLoc);
			_topPanel.add(_back);
			this.revalidate();
			this.repaint();
		} else{
			_back.setText(text);
		}
		_back.addActionListener(backListener);
	}
	
	public void setDictionary(ActionListener dictionaryListen){
		_dictRead = false;
		_dictionary.addActionListener(dictionaryListen);
	}
	
	public void updateScore(String newScoreString){
		_topPanel.removeAll();
		_score = new JLabel(newScoreString);
		_score.setFont(new Font("Cambria", Font.PLAIN, 20));
		_score.setForeground(Color.white);
		FontMetrics metrics = _score.getFontMetrics(new Font("Cambria", Font.PLAIN, 20));
		int width = SwingUtilities.computeStringWidth(metrics, newScoreString);
		_score.setLocation((994-width)/2, _topButtonLoc-3);
		_score.setSize(new Dimension(width,35));
		_topPanel.add(_score);
		_topPanel.add(_un);
		_topPanel.add(_back);
		this.repaint();
		this.revalidate();
	}

	public void addMiddleButton(String title, int width, ActionListener listener){
		_middleButton.setText(title);
		_middleButton.setSize(new Dimension(width, _buttonHeight));
		int y = _bottomButtonLoc;
		int x = (994-width)/2;
		_middleButton.setLocation(x, y);
		_middleButton.addActionListener(listener);
		_middleButton.setVisible(true);
	}

	public void setMainPanel(JPanel mainPanel){
		//Main Panel
		_mainPanel = mainPanel;
		_mainPanel.setBorder(BorderFactory.createEmptyBorder());
		_mainPanel.setSize(new Dimension(994,595));
		_mainPanel.setLocation(_mainPanelLocation);
		this.removeAll();
		this.add(_topPanel);
		this.add(_mainPanel);
		this.add(_bottomPanel);
		this.repaint();
		this.revalidate();
	}

	private class DictionaryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(_dictRead){
				new DictionaryInternalFrame(_driver.getDictionary());
			}
		}

	}

}
