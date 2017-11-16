/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorydumplab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author h63z978
 */
public class FindInfo {
    File memDump;
    static int numCards;
    
    FindInfo (File f){
       memDump = f;
       numCards = 0;
    }
    
    public void find(){
        try {

                
            byte[] dump = Files.readAllBytes(memDump.toPath());
            for(int i = 0; i < dump.length; i++){
                
                //finds % -- start senteniel
                if((int)dump[i] == 37){
                    //finds "B"
                    if((int)dump[i+1] == 66){
                        for(int e = i; e < (i+100); e++){
                            System.out.print((char)dump[e]);
                        }
                        System.out.println();
                        StringBuilder cardNum = new StringBuilder();
                        StringBuilder name = new StringBuilder();
                        StringBuilder expYear = new StringBuilder();
                        StringBuilder expMonth = new StringBuilder();
                        StringBuilder pin = new StringBuilder();
                        StringBuilder cvv = new StringBuilder();
                    
                        int tempIter = 0;
                        for(int cardNumIter = i+2; cardNumIter < i + 18; cardNumIter++){
                            
                            tempIter = cardNumIter;
                            //only adds if numeral 
                            if((int)dump[cardNumIter] > 47 && (int)dump[cardNumIter] < 58) 
                                cardNum.append((char)dump[cardNumIter]);
                        }
                        
                        //check for correct card num length and the next char being ^ Field Seperator
                        if(cardNum.length() == 16 && (int)dump[tempIter+1] == 94){
                            tempIter += 2;
                            //builds name field
                            for(int nameIter = tempIter; nameIter < (tempIter+27); nameIter++){
                                //makes sure the char is a letter or the /
                                if((int)dump[nameIter] > 64 && (int)dump[nameIter] < 91 || (int)dump[nameIter] > 96 && (int)dump[nameIter] < 123 || (int)dump[nameIter]  == 47)
                                    name.append((char)dump[nameIter]); 
                            } 
                        }
                        
                           
                        // make temp iter the space after the name
                        //check if there is a ^ after the name
                        tempIter += name.length();  
                        if ((int)dump[tempIter] == 94){
                            tempIter++;
                            for(int expIter = tempIter; expIter < (tempIter+4); expIter++){
                                if((int)dump[expIter] > 47 && (int)dump[expIter] < 58)
                                    if(expIter == tempIter || expIter == tempIter + 1)    
                                        expYear.append((char)dump[expIter]);
                                    else
                                        expMonth.append((char)dump[expIter]);
                            }
                        }
                        tempIter += 13;
                        for(int pinIter = tempIter; pinIter < tempIter+4; pinIter++){
                            if((int)dump[pinIter] > 47 && (int)dump[pinIter] < 58){
                                pin.append((char)dump[pinIter]);
                            }
                        }
                                
                               
                        
                        
                        
                        
                        
                       System.out.println(cardNum);
                       System.out.println(name);
                       System.out.println(expYear);
                       System.out.println(expMonth);
                       System.out.println(pin);
                        
                        
                    }
                    
                    
                    
                    
                    
                }
            }
        
            
    }   catch (IOException ex) {
            Logger.getLogger(FindInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
