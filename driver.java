	import java.awt.Color;
	import java.io.FileReader;
	import java.util.Scanner;

	public class driver {

		public static void main(String[] args) throws java.io.IOException
		{
			//create a variable that will scan the characters from the text called room 
			FileReader fr = new FileReader("room.txt");
			Scanner fileScanner = new Scanner(fr);
			//create an integer variable called width and height that will read the integer from room text 
			int width = fileScanner.nextInt();
			int height = fileScanner.nextInt();
			
			String chinput = fileScanner.nextLine();
			//create an integer called wallcount and dustcount that will represent the amount of walls and amount of dust 
			int wallcount = 0;
			
			int dustcount = 0;
			//initalize an EZ window with the size of width*32 and height* 32
			EZ.initialize(width*32,height*32); 
			//set the background color into a gray color 
			EZ.setBackgroundColor(new Color(130,124,123)); 
			//create an EZImage of Array and set the amount to 1000 
			EZImage wallArray[] = new EZImage[1000];
			EZImage dustArray[] = new EZImage[1000];
			//create an EZSound variable and set it up with the load.wav file 
			EZSound complete = EZ.addSound("load.wav");
			//create an integer i variable and set it equal to 0 
			int i = 0;
			//create an integer variable row to 0 
			//while the row is less than height 
			for(int row = 0; row < height; row++)
			{
				//the character input is the character from the file 
				chinput = fileScanner.nextLine();
				//show the character scanned 
				System.out.println(chinput);
				//create an integer column variable into 0
				//while the variable is less than width 
				for(int column = 0; column < width; column++)
				{
					//create a character ch variable and set it equal to the characters at the column 
					char ch = chinput.charAt(column);
					//initialize a switch statement with ch as the dependent 
					switch(ch)
					{
					//if the character is W
					case('W'):
					{
						//add an image of a wall 
						wallArray[i] = EZ.addImage("wall.png",(column*32)+16, (row*32)+16);
						//increase the increment of the wallcount by one 
						wallcount ++;
						break;
					}
					//if the character is D 
					case('D'):
					{
						//add an image of the dust called speck.png 
						dustArray[i] = EZ.addImage("speck.png", (column*32)+16, (row*32)+16);
						//increase the increment of the dust 
						dustcount ++;
					}
					default:
						//if there is no character 
					{
						//do nothing 
						break;
					}
					}
				//increase the increment of i 
					i++;
				}//close the loop for the column 
			
			}//close the for loop for the row 
			
			//create an integer variable called score and initialize it to 0 
			int score = 0;
			//create an EZSound variable called bgm and attach it to bgm.wav which will be the background music 
			EZSound bgm = EZ.addSound("bgm.wav");
			//play the bgm in a loop 
			bgm.loop();
			//load the roombrah icon 
			Roombrah icon = new Roombrah();
			//create the info of the wallArray and dustArray 
			icon.settileinfo(wallArray);
			icon.setdustinfo(dustArray);
			//while roombrah is on operation 
			while(true) {	
				//call the function move and have the icon move 
				icon.move();
				//call the trace function and have the icon leave a trail 
				icon.trace();	
				//set the score equal to the dcount of Roombrah file 
				score = icon.dcount;	
				//if the score is equal to dustcount variable 
				if(score==dustcount)
					
					//end the loop 
					break;	
					//refresh the graphics 
				EZ.refreshScreen();	
			}
			bgm.stop();
			complete.play();	// play the victory sound
			EZText text = EZ.addText(512, 40, "Finished", new Color(0, 0, 0), 100);
			text.setFont("Super Creative.ttf");
		

	}
	}



