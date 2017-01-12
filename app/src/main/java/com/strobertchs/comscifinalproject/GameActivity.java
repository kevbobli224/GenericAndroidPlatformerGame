package com.strobertchs.comscifinalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameActivity extends Activity {

    int temp;
    int screenWidth;
    int screenHeight;

    int jumpNoPushX;
    int jumpNoPushY;
    int dpadleftX;
    int dpadleftY;
    int dpadRightX;
    int dpadRightY;
    int punchButtonX;
    int punchButtonY;
    int charPositionX;
    int charPositionY;
    int enemyRobbieX;
    int enemyRobbieY;
    int grassPlatformX;
    int grassPlatformY;
    int grassPlatform2X;
    int grassPlatform2Y;
    int portalX;
    int portalY;
    int enemyBottleX;
    int enemyBottleY;

    int jumpNoPushBlockX;
    int jumpNoPushBlockY;
    int dpadleftBlockX;
    int dpadleftBlockY;
    int dpadRightBlockX;
    int dpadRightBlockY;
    int punchButtonBlockX;
    int punchButtonBlockY;
    int charBlockPositionX;
    int charBlockPositionY;
    int enemyRobbieBlockX;
    int enemyRobbieBlockY;
    int grassPlatformBlockX;
    int grassPlatformBlockY;
    int grassPlatform2BlockX;
    int grassPlatform2BlockY;
    int portalBlockX;
    int portalBlockY;
    int enemyBottleBlockX;
    int enemyBottleBlockY;

    int charBlockWidth;
    int charBlockHeight;
    int enemyRobbieBlockWidth;
    int enemyRobbieBlockHeight;
    int charBlockBottomGap = 3;
    int grassPlatformBlockWidth;
    int grassPlatformBlockHeight;
    int jumpNoPushBlockWidth;
    int jumpNoPushBlockHeight;
    int dpadLeftBlockWidth;
    int dpadLeftBlockHeight;
    int dpadRightBlockWidth;
    int dpadRightBlockHeight;
    int punchButtonBlockHeight;
    int punchButtonBlockWidth;
    int portalBlockHeight;
    int portalBlockWidth;
    int enemyBottleHeight;
    int enemyBottleWidth;

    boolean charMoveLeft;
    boolean charMoveRight;
    boolean charMoveUp;
    boolean charMoveDown;
    boolean charJab;
    boolean enemyRobbieMoveLeft = true;
    boolean enemiesDefeated = false;
    boolean enemyBottleMoveLeft = true;

    double blockSize;
    int numBlocksWide;
    int numBlocksHigh;
    int mysteriousBottomGapBlock = 15;

    int roundedCharWidth;
    int roundedCharHeight;
    int roundedRobbieWidth;
    int roundedRobbieHeight;
    int roundedGrassPlatformWidth;
    int roundedGrassPlatformHeight;
    int roundedJumpNoPushWidth;
    int roundedJumpNoPushHeight;
    int roundedDpadLeftWidth;
    int roundedDpadLeftHeight;
    int roundedDpadRightWidth;
    int roundedDpadRightHeight;
    int roundedPunchButtonWidth;
    int roundedPunchButtonHeight;
    int roundedPortalWidth;
    int roundedPortalHeight;
    int roundedEnemyBottleWidth;
    int roundedEnemyBottleHeight;

    boolean noPush = true;

    boolean noFlip = true;
    int ground1;
    boolean noGravity = false;

    int tempUp = 1;
    boolean tempUpFinal;

    Canvas canvas;
    GameActivityView gameActivityView;

    Bitmap grassPlatformBitmap;
    Bitmap enemyRobbieBitmap;
    Bitmap jumpNoPushBitmap, jumpPushBitmap;
    Bitmap dpadLeftBitmap;
    Bitmap dpadRightBitmap;
    Bitmap portalBitmap;
    Bitmap punchButtonBitmap;
    Bitmap charBitmap0, charBitmap1, charBitmap2, charBitmap3, charBitmap4, charBitmap5, charBitmap6, charBitmap7, charBitmap8, charBitmap9;
    Bitmap enemyPopBottleBitmap0,enemyPopBottleBitmap1,enemyPopBottleBitmap2,enemyPopBottleBitmap3,enemyPopBottleBitmap4,enemyPopBottleBitmap5,enemyPopBottleBitmap6,enemyPopBottleBitmap7,enemyPopBottleBitmap8,enemyPopBottleBitmap9;
    Bitmap charWalkBitmap0,charWalkBitmap1,charWalkBitmap2,charWalkBitmap3,charWalkBitmap4,charWalkBitmap5,charWalkBitmap6,charWalkBitmap7,charWalkBitmap8,charWalkBitmap9;
    Bitmap charJabBitmap0,charJabBitmap1,charJabBitmap2,charJabBitmap3,charJabBitmap4,charJabBitmap5,charJabBitmap6,charJabBitmap7,charJabBitmap8,charJabBitmap9;
    Bitmap[] charJabBitmapArray;
    Bitmap[] charBitmapArray;
    Bitmap[] charWalkBitmapArray;
    Bitmap[] enemyBottleBitmapArray;

    int charFrame = 0;

    //stats
    long lastFrameTime;
    int fps;
    int score;
    int hi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //   loadSound();
        configureDisplay();
        gameActivityView = new GameActivityView(this);
        setContentView(gameActivityView);


        jumpNoPushBlockX = 350;
        jumpNoPushBlockY = numBlocksHigh - mysteriousBottomGapBlock - 50;
        dpadleftBlockX = 0;
        dpadleftBlockY = numBlocksHigh - mysteriousBottomGapBlock -50;
        dpadRightBlockX = 40;
        dpadRightBlockY = numBlocksHigh - mysteriousBottomGapBlock - 50;
        punchButtonBlockX = 300;
        punchButtonBlockY = numBlocksHigh - mysteriousBottomGapBlock - 50;
        charBlockPositionX = 200;
        charBlockPositionY = 0;
        enemyRobbieBlockX = 360;
        enemyRobbieBlockY = 0;
        portalBlockX = 290;
        portalBlockY = 103;
        grassPlatformBlockX = 250;
        grassPlatformBlockY = 150;
        grassPlatform2BlockX = 100;
        grassPlatform2BlockY = 100;
        enemyBottleX = 360;
        enemyBottleY = 0;


    }

    class GameActivityView extends SurfaceView implements Runnable {
        Thread ourThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playingGame;
        Paint paint;

        public GameActivityView(Context context) {
            super(context);
            ourHolder = getHolder();
            paint = new Paint();

            charMoveDown = true;
            /*
            //our starting snake
            getSnake();
            //get an apple to munch
            getApple();
            */
        }

        public void gravity() {
            if (noGravity == false) {
                charBlockPositionY = charBlockPositionY + 10;
            }
        }

        public int applyGravityTo(int positionY){
            positionY = positionY + 10;
            return positionY;
        }

        public void checkJump(){
            //jump
            if (tempUpFinal && (tempUp == 10)){
                charMoveUp = false;
                tempUp = 1;
                tempUpFinal = false;
            }

        }

        public void jumpIfApplicable(){
            //10 stage jump
            if (charMoveUp && (charBlockPositionY > 0)) {
                if (tempUp == 10){
                    tempUpFinal = true;
                }
                if (tempUp == 9) {
                    charBlockPositionY = charBlockPositionY - 1;
                    tempUp = 10;
                }
                if (tempUp == 8) {
                    charBlockPositionY = charBlockPositionY - 2;
                    tempUp = 9;
                }
                if (tempUp == 7) {
                    charBlockPositionY = charBlockPositionY - 5;
                    tempUp = 8;
                }
                if (tempUp == 6) {
                    charBlockPositionY = charBlockPositionY - 12;
                    tempUp = 7;
                }
                if (tempUp == 5){
                    charBlockPositionY = charBlockPositionY - 18;
                    tempUp = 6;
                }
                if (tempUp == 4){
                    charBlockPositionY = charBlockPositionY - 23;
                    tempUp = 5;
                }
                if (tempUp == 3){
                    charBlockPositionY = charBlockPositionY - 26;
                    tempUp = 4;
                }
                if (tempUp == 2){
                    charBlockPositionY = charBlockPositionY - 28;
                    tempUp = 3;
                }
                if (tempUp == 1){
                    charBlockPositionY = charBlockPositionY - 30;
                    tempUp = 2;
                }
            }

            else if (charMoveUp && (charBlockPositionY < 0)){
                charMoveUp = false;
                tempUp = 1;
                tempUpFinal = false;
            }
        }

        public boolean generalButtonTouchEvent(float motionEventX, float motionEventY, int xValue, int yValue, int blockWidth, int blockHeight){
            if (((motionEventX >= xValue) && (motionEventX <= (xValue + (int) Math.round(blockWidth * blockSize))))
                    && ((motionEventY >= yValue) && (motionEventY <= (yValue + (int) Math.round(blockHeight * blockSize))))){
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        public void run() {
            while (playingGame) {

                updateGame();
                drawGame();
                controlFPS();
                checkJump();
                charFrame++;

                if(charFrame > 9){
                    charFrame = 0;
                    charJab = false;
                }

            }

        }

        public void updateGame() {

            //noGravity is always set to false so if it was true in the previous frame it will be set to false again
            noGravity = false;

            //checks if the character is along the width of the platform
            if (((charBlockPositionX + (charBlockWidth/2)) > grassPlatformBlockX) && ((charBlockPositionX + (charBlockWidth/2)) < (grassPlatformBlockX + grassPlatformBlockWidth))) {
                //checks if the character is within range of (10 block to 0 blocks above) the platform
                if (((charBlockPositionY + charBlockHeight - charBlockBottomGap) >= grassPlatformBlockY - 10) && ((charBlockPositionY + charBlockHeight - charBlockBottomGap) <= grassPlatformBlockY)) {
                    //sets noGravity to true so the character does not continue falling
                    noGravity = true;
                    //places the character on the platform
                    charBlockPositionY = grassPlatformBlockY - charBlockHeight + charBlockBottomGap;
                }
            }
            //second platform
            if (((charBlockPositionX + (charBlockWidth/2)) > grassPlatform2BlockX) && ((charBlockPositionX + (charBlockWidth/2)) < (grassPlatform2BlockX + grassPlatformBlockWidth))) {
                if (((charBlockPositionY + charBlockHeight - charBlockBottomGap) >= grassPlatform2BlockY - 10) && ((charBlockPositionY + charBlockHeight - charBlockBottomGap) <= grassPlatform2BlockY)) {
                    noGravity = true;
                    charBlockPositionY = grassPlatform2BlockY - charBlockHeight + charBlockBottomGap;
                }
            }


            // "charPosition.x < numBlocksWide - charWidth" restricts it from going any further to the right
            if (charMoveRight && (charBlockPositionX < (numBlocksWide - charBlockWidth))) {
                //move char right by 7
                charBlockPositionX = charBlockPositionX + 3;
            }

            // "charPosition.x > 0" restricts it from going any further to the left of it is smaller than 0
            if (charMoveLeft && (charBlockPositionX > 0)) {
                //move char left by 7
                charBlockPositionX = charBlockPositionX - 3;
            }




            //call the jumpIfApplicable method
            jumpIfApplicable();

            //if the player is in the air, apply gravity
            if (charBlockPositionY < (ground1 - charBlockHeight)) {
                gravity();
            }

            //if the player has hit the ground or lower, set the player on the ground
            if (charBlockPositionY >= (ground1 - charBlockHeight)){
                charBlockPositionY = ground1 - charBlockHeight;
            }

            //if enemy is in the air, apply gravity
            if (enemyRobbieBlockY < (ground1 - enemyRobbieBlockHeight)){
                enemyRobbieBlockY = applyGravityTo(enemyRobbieBlockY);
            }

            if (enemyRobbieBlockX > (250 - enemyRobbieBlockWidth)){
                enemyRobbieMoveLeft = true;
            }

            if (enemyRobbieMoveLeft){
                enemyRobbieBlockX = enemyRobbieBlockX - 3;
            }

            if (enemyRobbieBlockX < 0){
                enemyRobbieMoveLeft = false;
            }

            if (!enemyRobbieMoveLeft){
                enemyRobbieBlockX = enemyRobbieBlockX + 3;
            }

            //collision detection between the char and enemyrobbie
            if ((Math.abs((charBlockPositionX + (charBlockWidth/2)) - (enemyRobbieBlockX + (enemyRobbieBlockWidth/2))) <= (charBlockWidth + enemyRobbieBlockWidth)/2 - 15)
                    && (Math.abs((charBlockPositionY + (charBlockHeight/2)) - (enemyRobbieBlockY + (enemyRobbieBlockHeight/2))) <= (charBlockHeight + enemyRobbieBlockHeight)/2 - 10)){
                charBlockPositionX = 100;
                charBlockPositionY = 0;
            }

            //if char is jumps above the screen, make it reappear at the bottom
            /*
            if (charPosition.y < 0) {
               charPosition.y = screenHeight - charWidth;
            }
            */
            /*
            if ((((grassPlatformBlockX + (grassPlatformBlockWidth/2)) - (charBlockPositionX + (charBlockWidth/2))) < 50)
                    && (((grassPlatformBlockX + (grassPlatformBlockWidth/2)) - (charBlockPositionX + (charBlockWidth/2))) > -50)){
                noGravity = true;
            }
            
            if ((((grassPlatformBlockX + (grassPlatformBlockWidth/2)) - (charBlockPositionX + (charBlockWidth/2))) > 50)
                    || (((grassPlatformBlockX + (grassPlatformBlockWidth/2)) - (charBlockPositionX + (charBlockWidth/2))) < -50)){
                noGravity = false;
            }
     */

            dpadleftX = (int) Math.round(dpadleftBlockX * blockSize);
            dpadleftY = (int) Math.round(dpadleftBlockY * blockSize);
            dpadRightX = (int) Math.round(dpadRightBlockX * blockSize);
            dpadRightY = (int) Math.round(dpadRightBlockY * blockSize);
            portalX = (int) Math.round(portalBlockX * blockSize);
            portalY = (int) Math.round(portalBlockY * blockSize);
            punchButtonX = (int) Math.round(punchButtonBlockX * blockSize);
            punchButtonY = (int) Math.round(punchButtonBlockY * blockSize);
            charPositionX = (int) Math.round(charBlockPositionX * blockSize);
            charPositionY = (int) Math.round(charBlockPositionY * blockSize);
            enemyRobbieX = (int) Math.round(enemyRobbieBlockX * blockSize);
            enemyRobbieY = (int) Math.round(enemyRobbieBlockY * blockSize);
            grassPlatformX = (int) Math.round(grassPlatformBlockX * blockSize);
            grassPlatformY = (int) Math.round(grassPlatformBlockY * blockSize);
            grassPlatform2X = (int) Math.round(grassPlatform2BlockX * blockSize);
            grassPlatform2Y = (int) Math.round(grassPlatform2BlockY * blockSize);
            jumpNoPushX = (int) Math.round(jumpNoPushBlockX * blockSize);
            jumpNoPushY = (int) Math.round(jumpNoPushBlockY * blockSize);
            enemyBottleX = (int) Math.round(enemyBottleX * blockSize);
            enemyBottleY = (int) Math.round(enemyBottleY * blockSize);


        }

        public void drawGame() {

            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                //Paint paint = new Paint();
                canvas.drawColor(Color.BLACK);//the background
                paint.setColor(Color.argb(255, 255, 255, 255));
                paint.setTextSize(25);
                canvas.drawText(charBlockPositionX + ", " + charBlockPositionY +" fps:" + fps +", charFrame: " + charFrame + " numBlocksWide: " + numBlocksWide + " numBlocksHigh: " + numBlocksHigh
                        + " " + (charBlockPositionY + charBlockHeight - charBlockBottomGap) +" "+ temp, 20, 40, paint);

                canvas.drawBitmap(enemyRobbieBitmap, enemyRobbieX, enemyRobbieY, paint);
                canvas.drawBitmap(portalBitmap,portalX,portalY, paint);
                //draw both grass platforms
                canvas.drawBitmap(grassPlatformBitmap, grassPlatformX, grassPlatformY, paint);
                canvas.drawBitmap(grassPlatformBitmap, grassPlatform2X, grassPlatform2Y, paint);

                if (charMoveRight){
                    noFlip = true;
                }
                if (charMoveLeft){
                    noFlip = false;
                }

                if (noFlip) {
                    /*
                    if (firstTouchCycle){
                        charPositionX = (int) Math.round((charBlockPositionX - (charBlockWidth/2)) * blockSize);
                        //firstTouchCycle = false;
                    }
                    */

                    if (charMoveRight) {
                        canvas.drawBitmap(charWalkBitmapArray[charFrame], charPositionX, charPositionY, paint);
                    } else if (charJab) {
                        if(charBlockPositionX < (numBlocksWide - charBlockWidth)){
                            charBlockPositionX = charBlockPositionX + 3;
                        }
                        canvas.drawBitmap(charJabBitmapArray[charFrame], charPositionX, charPositionY, paint);
                    } else {
                        canvas.drawBitmap(charBitmapArray[charFrame], charPositionX, charPositionY, paint);
                    }
                }

                if (!noFlip){
                    /*
                    if (firstTouchCycle){
                        charPositionX = (int) Math.round((charBlockPositionX + (charBlockWidth/2)) * blockSize);
                        //firstTouchCycle = false;
                    }
                    */
                    //Flipping the character when facing to the left
                    Matrix flipHorizontalMatrix = new Matrix();
                    flipHorizontalMatrix.setScale(-1,1);
                    charPositionX = charPositionX + (int) Math.round(charBlockWidth * blockSize);
                    flipHorizontalMatrix.postTranslate(charPositionX, charPositionY);


                    if (charMoveLeft){
                        canvas.drawBitmap(charWalkBitmapArray[charFrame], flipHorizontalMatrix, paint);
                    }
                    else if (charJab) {
                        if (charBlockPositionX > 0) {
                            charBlockPositionX = charBlockPositionX - 3;
                        }
                        canvas.drawBitmap(charJabBitmapArray[charFrame], flipHorizontalMatrix, paint);
                    }

                    else {
                        canvas.drawBitmap(charBitmapArray[charFrame], flipHorizontalMatrix, paint);
                    }
                }

                canvas.drawBitmap(dpadLeftBitmap, dpadleftX, dpadleftY, paint);
                canvas.drawBitmap(dpadRightBitmap, dpadRightX, dpadRightY, paint);
                canvas.drawBitmap(punchButtonBitmap, punchButtonX, punchButtonY, paint);

                //if the button is not being pushed down
                if (noPush) {
                    canvas.drawBitmap(jumpNoPushBitmap, jumpNoPushX, jumpNoPushY, paint);
                }
                //if the button is being pushed down
                if (!noPush){
                    canvas.drawBitmap(jumpPushBitmap, jumpNoPushX, jumpNoPushY, paint);
                }

                ourHolder.unlockCanvasAndPost(canvas);

            }

        }

        public void controlFPS() {
            int FPS = 80;
            long timeThisFrame = (System.currentTimeMillis() - lastFrameTime);
            long timeToSleep = 100 - FPS;
            if (timeThisFrame > 0) {
                fps = (int) (1000 / FPS);
            }
            if (timeToSleep > 0) {

                try {
                    ourThread.sleep(timeToSleep);
                } catch (InterruptedException e) {
                    //Print an error message to the console
                    Log.e("error", "failed to load sound files");
                }

            }
            lastFrameTime = System.currentTimeMillis();
        }


        public void pause() {
            playingGame = false;
            try {
                ourThread.join();
            } catch (InterruptedException e) {
            }

        }

        public void resume() {
            playingGame = true;
            ourThread = new Thread(this);
            ourThread.start();
        }


        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:

                    if (generalButtonTouchEvent(motionEvent.getX(), motionEvent.getY(), jumpNoPushX, jumpNoPushY, jumpNoPushBlockWidth, jumpNoPushBlockHeight)){
                        //making it so that you can only jump once this is hardcoded for this map specifically for now.
                        if (noGravity == true || charBlockPositionY == ground1 - charBlockHeight){
                        charMoveUp = true;
                        noPush = false;
                        }
                        else{
                            noPush = false;
                        }
                }
                    /*if (motionEvent.getY() <= screenHeight / 2){
                        charMoveUp = true;
                    }
                    */
                    //move right
                    if (generalButtonTouchEvent(motionEvent.getX(), motionEvent.getY(), dpadRightX, dpadRightY, dpadRightBlockWidth, dpadRightBlockHeight)) {
                        charMoveRight = true;
                        charMoveLeft = false;
                        charJab = false;
                        //firstTouchCycle = true;

                    }
                    //move left
                    if (generalButtonTouchEvent(motionEvent.getX(), motionEvent.getY(), dpadleftX, dpadleftY, dpadLeftBlockWidth, dpadLeftBlockHeight)){
                        charMoveLeft = true;
                        charMoveRight = false;
                        charJab = false;
                        //firstTouchCycle = true;

                    }
                    //Jab animation when we click the jab button
                    if (generalButtonTouchEvent(motionEvent.getX(),motionEvent.getY(),punchButtonX,punchButtonY, punchButtonBlockWidth, punchButtonBlockHeight)){
                        charMoveRight = false;
                        charMoveLeft = false;
                        charJab = true;

                    }

                    break;


                case MotionEvent.ACTION_UP:

                    charMoveLeft = false;
                    charMoveRight = false;
                    noPush = true;
                    break;
            }
            return true;

        }


    }

    @Override
    protected void onStop() {
        super.onStop();

        while (true) {
            gameActivityView.pause();
            break;
        }

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameActivityView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameActivityView.pause();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            gameActivityView.pause();


            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return false;
    }

    public void configureDisplay(){
        //find out the width and height of the screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        //Determine the size of each block/place on the game board
        blockSize = screenWidth/400.0;

        //Determine how many game blocks will fit into the height and width
        //Leave one block for the score at the top
        numBlocksWide = 400;
        numBlocksHigh = (int) Math.round(screenHeight/blockSize);

        charBlockWidth = 30;
        charBlockHeight = 50;
        enemyRobbieBlockWidth = 33;
        enemyRobbieBlockHeight = 75;
        grassPlatformBlockWidth = 100;
        grassPlatformBlockHeight = 18;
        jumpNoPushBlockWidth = 40;
        jumpNoPushBlockHeight = 40;
        dpadLeftBlockWidth = 40;
        dpadLeftBlockHeight = 40;
        dpadRightBlockHeight = 40;
        dpadRightBlockWidth = 40;
        portalBlockHeight = 50;
        portalBlockWidth = 20;
        punchButtonBlockWidth = 40;
        punchButtonBlockHeight = 40;


        roundedCharWidth = (int) Math.round(blockSize * charBlockWidth);
        roundedCharHeight = (int) Math.round(blockSize * charBlockHeight);
        roundedRobbieWidth = (int) Math.round(blockSize * enemyRobbieBlockWidth);
        roundedRobbieHeight = (int) Math.round(blockSize * enemyRobbieBlockHeight);
        roundedGrassPlatformWidth = (int) Math.round(blockSize * grassPlatformBlockWidth);
        roundedGrassPlatformHeight = (int) Math.round(blockSize * grassPlatformBlockHeight);
        roundedJumpNoPushWidth = (int) Math.round(blockSize * jumpNoPushBlockWidth);
        roundedJumpNoPushHeight = (int) Math.round(blockSize * jumpNoPushBlockHeight);
        roundedDpadLeftHeight = (int) Math.round(blockSize * dpadLeftBlockWidth);
        roundedDpadLeftWidth = (int) Math.round(blockSize * dpadLeftBlockHeight);
        roundedDpadRightHeight = (int) Math.round(blockSize * dpadRightBlockHeight);
        roundedDpadRightWidth = (int) Math.round(blockSize * dpadRightBlockWidth);
        roundedPortalHeight = (int) Math.round(blockSize * portalBlockHeight);
        roundedPortalWidth = (int) Math.round(blockSize * portalBlockHeight);
        roundedPunchButtonHeight = (int) Math.round(blockSize * punchButtonBlockHeight);
        roundedPunchButtonWidth = (int) Math.round(blockSize * punchButtonBlockWidth);


        ground1 = numBlocksHigh - 30;

        //Load bitmaps
        charBitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.idle1);
        charBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.idle2);
        charBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.idle3);
        charBitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.idle4);
        charBitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.idle5);
        charBitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.idle6);
        charBitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.idle7);
        charBitmap7 = BitmapFactory.decodeResource(getResources(), R.drawable.idle8);
        charBitmap8 = BitmapFactory.decodeResource(getResources(), R.drawable.idle9);
        charBitmap9 = BitmapFactory.decodeResource(getResources(), R.drawable.idle10);
        charWalkBitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_000);
        charWalkBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_001);
        charWalkBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_002);
        charWalkBitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_003);
        charWalkBitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_004);
        charWalkBitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_005);
        charWalkBitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_006);
        charWalkBitmap7 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_007);
        charWalkBitmap8 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_008);
        charWalkBitmap9 = BitmapFactory.decodeResource(getResources(), R.drawable.walk_009);
        charJabBitmap0 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_000);
        charJabBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_001);
        charJabBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_002);
        charJabBitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_003);
        charJabBitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_004);
        charJabBitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_005);
        charJabBitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_006);
        charJabBitmap7 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_007);
        charJabBitmap8 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_008);
        charJabBitmap9 = BitmapFactory.decodeResource(getResources(), R.drawable.jab_009);
        grassPlatformBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grassplatform);
        jumpNoPushBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jumpnopush);
        jumpPushBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jumppush);
        dpadLeftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dpadleft);
        dpadRightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dpadright);
        portalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.portal);
        enemyPopBottleBitmap0 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_000);
        enemyPopBottleBitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_001);
        enemyPopBottleBitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_002);
        enemyPopBottleBitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_003);
        enemyPopBottleBitmap4 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_004);
        enemyPopBottleBitmap5 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_005);
        enemyPopBottleBitmap6 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_006);
        enemyPopBottleBitmap7 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_007);
        enemyPopBottleBitmap8 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_008);
        enemyPopBottleBitmap9 = BitmapFactory.decodeResource(getResources(),R.drawable.walkbottle_009);
        enemyRobbieBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lazytownrobbie);
        punchButtonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.punchbutton);


        //scale the bitmaps to match the block size
        charBitmap0 = Bitmap.createScaledBitmap(charBitmap0,roundedCharWidth, roundedCharHeight, false);
        charBitmap1 = Bitmap.createScaledBitmap(charBitmap1,roundedCharWidth, roundedCharHeight, false);
        charBitmap2 = Bitmap.createScaledBitmap(charBitmap2,roundedCharWidth, roundedCharHeight, false);
        charBitmap3 = Bitmap.createScaledBitmap(charBitmap3,roundedCharWidth, roundedCharHeight, false);
        charBitmap4 = Bitmap.createScaledBitmap(charBitmap4,roundedCharWidth, roundedCharHeight, false);
        charBitmap5 = Bitmap.createScaledBitmap(charBitmap5,roundedCharWidth, roundedCharHeight, false);
        charBitmap6 = Bitmap.createScaledBitmap(charBitmap6,roundedCharWidth, roundedCharHeight, false);
        charBitmap7 = Bitmap.createScaledBitmap(charBitmap7,roundedCharWidth, roundedCharHeight, false);
        charBitmap8 = Bitmap.createScaledBitmap(charBitmap8,roundedCharWidth, roundedCharHeight, false);
        charBitmap9 = Bitmap.createScaledBitmap(charBitmap9,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap0 = Bitmap.createScaledBitmap(charWalkBitmap0,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap1 = Bitmap.createScaledBitmap(charWalkBitmap1,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap2 = Bitmap.createScaledBitmap(charWalkBitmap2,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap3 = Bitmap.createScaledBitmap(charWalkBitmap3,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap4 = Bitmap.createScaledBitmap(charWalkBitmap4,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap5 = Bitmap.createScaledBitmap(charWalkBitmap5,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap6 = Bitmap.createScaledBitmap(charWalkBitmap6,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap7 = Bitmap.createScaledBitmap(charWalkBitmap7,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap8 = Bitmap.createScaledBitmap(charWalkBitmap8,roundedCharWidth, roundedCharHeight, false);
        charWalkBitmap9 = Bitmap.createScaledBitmap(charWalkBitmap9,roundedCharWidth, roundedCharHeight, false);
        charJabBitmap0 = Bitmap.createScaledBitmap(charJabBitmap0,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap1 = Bitmap.createScaledBitmap(charJabBitmap1,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap2 = Bitmap.createScaledBitmap(charJabBitmap2,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap3 = Bitmap.createScaledBitmap(charJabBitmap3,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap4 = Bitmap.createScaledBitmap(charJabBitmap4,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap5 = Bitmap.createScaledBitmap(charJabBitmap5,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap6 = Bitmap.createScaledBitmap(charJabBitmap6,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap7 = Bitmap.createScaledBitmap(charJabBitmap7,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap8 = Bitmap.createScaledBitmap(charJabBitmap8,roundedCharWidth+14,roundedCharHeight, false);
        charJabBitmap9 = Bitmap.createScaledBitmap(charJabBitmap9,roundedCharWidth+14,roundedCharHeight, false);
        grassPlatformBitmap = Bitmap.createScaledBitmap(grassPlatformBitmap, roundedGrassPlatformWidth, roundedGrassPlatformHeight, false);
        jumpNoPushBitmap = Bitmap.createScaledBitmap(jumpNoPushBitmap,roundedJumpNoPushWidth, roundedJumpNoPushHeight, false);
        jumpPushBitmap = Bitmap.createScaledBitmap(jumpPushBitmap,roundedJumpNoPushWidth, roundedJumpNoPushHeight, false);
        dpadLeftBitmap = Bitmap.createScaledBitmap(dpadLeftBitmap,roundedDpadLeftWidth,roundedDpadLeftHeight, false);
        dpadRightBitmap = Bitmap.createScaledBitmap(dpadRightBitmap, roundedDpadRightWidth, roundedDpadRightHeight, false);
        portalBitmap = Bitmap.createScaledBitmap(portalBitmap, roundedPortalWidth, roundedPortalHeight, false);
        punchButtonBitmap = Bitmap.createScaledBitmap(punchButtonBitmap,roundedPunchButtonWidth,roundedPunchButtonHeight,false);

        enemyRobbieBitmap = Bitmap.createScaledBitmap(enemyRobbieBitmap,roundedRobbieWidth, roundedRobbieHeight, false);


        charBitmapArray = new Bitmap[]{charBitmap0, charBitmap1, charBitmap2, charBitmap3, charBitmap4, charBitmap5, charBitmap6, charBitmap7, charBitmap8, charBitmap9};
        charWalkBitmapArray = new Bitmap[]{charWalkBitmap0, charWalkBitmap1, charWalkBitmap2, charWalkBitmap3, charWalkBitmap4, charWalkBitmap5, charWalkBitmap6, charWalkBitmap7, charWalkBitmap8, charWalkBitmap9};
        charJabBitmapArray = new Bitmap[]{charJabBitmap0,charJabBitmap1,charJabBitmap2,charJabBitmap3,charJabBitmap4,charJabBitmap5,charJabBitmap6,charJabBitmap7,charJabBitmap8,charJabBitmap9};

    }


}

