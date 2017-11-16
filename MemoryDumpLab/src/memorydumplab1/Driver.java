

package memorydumplab1;

import java.io.File;


/* Aaron McCarthy
 *
 * 
 */
public class Driver {

   
    public static void main(String[] args) {
        File file = new File("memorydump.dmp");
        FindInfo finder = new FindInfo (file);
        finder.find();
                
        
    }
}
