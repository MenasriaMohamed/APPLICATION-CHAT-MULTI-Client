/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_client;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author ezi
 */

public class EmailValidator {
     private Pattern pattern;
     private Matcher matcher;
     boolean ok = false ;
     
    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
   private static final String NAME_PATTERN = "^[_A-Za-z]+" ;
    
    
    public EmailValidator() {
      
    }

    /**
     * Validate hex with regular expression
     * 
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
    
    public String  validate_name(String hex)
    {          
        if(hex.equalsIgnoreCase("") || hex== null)
        { 
         return "Error User Name can't be empty" ;
        }else{
           if(hex.length()<4 || hex.length()> 50 )  
           {return "Error User Name  length must be more then 4 !!" ;
           } else{   
         pattern = Pattern.compile(NAME_PATTERN);
         matcher = pattern.matcher(hex);
         if(!matcher.matches()){
            return "Error The name contain only alpha" ;         
           }    
          }
         }       
          return "" ;
       }      
    
    public String validate_password(String hex , String confirm)
    {
          if(hex.equalsIgnoreCase("") || hex== null)
        { 
         return "Error Password can't be empty" ;}else{       
           if(hex.length()<4 || hex.length()> 50 )  
           {return "Error Password  length must be more then 4 !!" ; 
           }else{
              if(!hex.equals(confirm)) {
                return "Error  of confermation Password !!" ;
              }
           
           }
          
          
          }
               
               
               
               
           
         
    
          
    
             return "";       
    }

}