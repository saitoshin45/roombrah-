

	import java.awt.Color;
	import java.util.Random;

	public class Roombrah 
	{	// Creates a class called Roombrah
		//create an integer variable of Xpos and Ypos set it into 200 
		int Xpos = 200;		
		int Ypos = 200;	
		//create an EZImage called icon
		EZImage icon;
		//create an integer variable called Xdir and Ydir and set it into 4
		int Xdir = 4;	
		int Ydir= 4;
		//create an integer called dmoved an set it into 0
		int dmoved = 0;	
		//create an integer called max_d and set it into 1000
		final int max_d = 1000;	
		//create an array of EZImage named WallArray and DirtArray
		EZImage[]WallArray;	
		EZImage[]DirtArray;	
		//create an integer called dcount and set it into 0 
		int dcount = 0;		
		//create an EZSound file called collect 
		EZSound capture = EZ.addSound("collect.wav");	
		
		public Roombrah(){	
			//create a function called Roombrah which will load the icon 
			icon = EZ.addImage("roombrah.png", Xpos, Ypos);	
		}
		void settileinfo(EZImage[]tileInfo) {	// Creates a function called settileinfo which takes EZImage[]tileInfo 
			WallArray = tileInfo;	// set WallArray = to tileInfo 
		}
		void setdustinfo(EZImage[]blockInfo) {	// Creates a function called setBlockInfromation which takes EZImage[]blockInfo
			DirtArray = blockInfo;	// sets the myDirtBlocks variable to be equal to blockInfo
		}
		//create a function called the change direction 
		public void changeDirection() {	
			//make a random variable rd to generate random
			Random rd = new Random();	
			//make an integer variable called randNum
			int randNum;	
			//generate a random generator that will choose an integer between 0 and 1
			randNum = rd.nextInt(2);
			//if the randNum is 0 
			if (randNum == 0) {
				//set Xir = -5 
				Xdir = -5;	
			}
			//if randNum is 1 
			if (randNum == 1) {	
				Xdir = 5;		// set Xdir = 5
			}
			randNum = rd.nextInt(2);	//randomanly pick a number between 0 and 1 
			if (randNum == 0) {
				//if the randNum is 0 then Y = -5
				Ydir = -5;	
			}
			if (randNum == 1) {	// If randomNumber is equal to 1...
				Ydir = 5;		// set directionY equal to 5
			}	
		}
		boolean Roombrahtouchwall(int x, int y) {	
			//create a function if roombrah touches the wall 
			for(int i = 0; i < WallArray.length;i++)
				//if the wallArray is not empty 
				if(WallArray[i] != null){	
					// Looks through the entire array of dirt pictures
					if(WallArray[i].isPointInElement(x,y)){	
						// return true and have the icon bounce off 
						return true;	
					}
				}
			
		return false; // if no tiles were touched, then return false
		}
		//create a function if the roombrah is touching the dirt 
		boolean Roombrahtouchdirt(int x, int y) {	
			// Looks through the entire array of dirt pictures
				for (int i = 0; i < DirtArray.length; i++)
					//if the slot is not full 
					if(DirtArray[i] !=null) {	
						// if the roombrah inside the dirt block
						if(DirtArray[i].isPointInElement(x, y)) {
							// translate the dirt block to 0,0
							DirtArray[i].translateTo(0,0);	
							// play the collect sound
							capture.play();	
							// increment the dcount by 1 
							dcount++;	
							//the statement is true if touches the dirt 
							return true;	
						}
					}
				//if there is no blocks touching the icon 
				return false;	
		}
		//create a function called move 
		public void move() {	
			//create an integer variable called newX and newY 
			int newX;
			int newY;	
			//set the newX and Y to the sum of the corresponding X and Y 
			newX = Xpos + Xdir;	
			newY = Ypos + Ydir;	
			//while the touchwall function is true 
			while(Roombrahtouchwall(newX, newY)) {	
				//call the changeDirection function 
				changeDirection();	 
				//update the newX and newY with the positions and direction 
				newX = Xpos + Xdir;	
				newY = Ypos + Ydir;	
			}
			//while the function touchdirt is true 
			while(Roombrahtouchdirt(Xpos, Ypos)) {	
				//update the newX and newY with the positions and direction 
				newX = Xpos + Xdir;	
				newY = Ypos + Ydir;	
			}
			//update the position with the sum of itself and direction
			Xpos = Xpos + Xdir;	
			Ypos = Ypos + Ydir;	
			//increase the distance by an increment of 10 
			dmoved+=10;	
			//if the distance moved is greater than the maximum distance 
			if (dmoved > max_d) {	
				//call upon the changeDirection function 
				changeDirection();	
				//reset the dmoved to 0 
				dmoved = 0;	
			}
			//have the icon translate to Xpos and Ypos 
			icon.translateTo(Xpos, Ypos);	 
		}

		public void trace() {	// Creates a function called trail
			//create an EZCircle variable named circle and with a gray color 
			EZCircle circle;	
			circle = EZ.addCircle(Xpos, Ypos, 10, 10, Color.lightGray, false);	
			//push back the circle one layer back 
			circle.pushBackOneLayer();	
		}
		
	}



