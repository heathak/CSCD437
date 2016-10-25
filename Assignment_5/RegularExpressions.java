import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions
{
   public static void main(String [] args)
   {
      Scanner kb = new Scanner(System.in);
      String regex = null, input = null;
      char choice = '\0';
      while((int)choice != (int)'q')
      {
         choice = menu(kb);
         switch(choice)
         {
            case 'a':   regex = testSSN();
                        break;
            case 'b':   regex = testUSPhone();
                        break;
            case 'c':   regex = testEmail();
                        break;
            case 'd':   regex = testName();
                        break;
            case 'e':   regex = testDate();
                        break;
            case 'f':   regex = testAddress();
                        break;
            case 'g':   regex = testCityStZip();
                        break;
            case 'h':   regex = testMilTime();
                        break;
            case 'i':   regex = testUSCurrency();
                        break;
            case 'j':   regex = testURL();
                        break;
            case 'k':   regex = testPassword();
                        break;
            case 'l':   regex = testIonWords();
                        break;
            default:    break;
         }
         if((int)choice != (int)'q')
         {
            System.out.print("Enter string: ");
            input = kb.nextLine();Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            System.out.println(matcher.matches());
         }
      }
   }
   
   public static char menu(Scanner kb)
   {
      char choice = '\0';
      while((int)choice < (int)'a' || ((int)choice > (int)'l' && (int)choice != (int)'q'))
      {
         System.out.println("MENU");
         System.out.println("a. Social Security Number");
         System.out.println("b. US Phone Number");
         System.out.println("c. E-mail address");
         System.out.println("d. Name on a class roster, assuming one or more middle initials - Last name, First name, MI");
         System.out.println("e. Date in MM-DD-YYYY format");
         System.out.println("f. House address - Street number, street name, abbreviation for road, street, boulevard, or avenue");
         System.out.println("g. City followed by state followed by zip as it should appear on a letter");
         System.out.println("h. Military time, including seconds");
         System.out.println("i. US Currency down to the penny (ex. $123,456,789.23)");
         System.out.println("j. URL, including http:// (upper and lower case should be accepted)");
         System.out.println("k. A password that contains at least 10 characters and includes at least one upper case character, lower case character, digit, punctuation mark, and does not have more than 3 consecutive lower case characters");
         System.out.println("l. All words containing an odd number of alphabetic characters, ending in \"ion\"");
         System.out.println("q. quit");
         System.out.println("Make a selection: ");
         choice = (char)kb.nextLine().charAt(0);
      }
      return choice;
   }
   
   public static String testSSN()
   {
      //allows 123456789, 123 45 6789, 123-45-6789, can't start with 9 or 666, no group may be all 0
      return "^(?!6{3}|0{3}|9\\d{2})\\d{3}\\-(?!0{2})\\d{2}\\-(?!0{4})\\d{4}|(?!6{3}|0{3}|9\\d{2})\\d{3}\\ (?!0{2})\\d{2}\\ (?!0{4})\\d{4}|(?!6{3}|0{3}|9\\d{2})\\d{3}(?!0{2})\\d{2}(?!0{4})\\d{4}";
   }
   
   public static String testUSPhone()
   {
      //allows (123)465-7890, (123)4567890, (123)456 7890, 1234567890, 123-456-7890
      return "\\d{3}-\\d{3}-\\d{4}|\\(\\d{3}\\)\\d{3}-? ?\\d{4}|\\d{3} \\d{3} \\d{4}|\\d{10}";
   }
   
   public static String testEmail()
   {
      //local-part allows characters A-Za-z0-9!#$%&'*+-/=?^_`{|}~., 1 to 30, can't start with dash, period can't repeat
      //domain name allows characters A-Za-z0-9_.-~, 1 to 30, can't start with a dash, period can't repeat
      //tld allows A-Za-z0-9, 2 to 6 characters
      return "^(?!-)(?!.*\\.\\.)[A-Za-z0-9!#$%&'*+-/=?^_`{|}~.]{1,30}@(?!-)[A-Za-z0-9_.-~]{1,30}\\.[A-Za-z0-9]{2,6}$";
   }
   
   public static String testName()
   {
      //first and last name allow A-Za-z-' and space, 1 to 20, middle initial(s) can be single letters A-Za-z
      return "^[A-Za-z-' ]{1,20}, [A-Za-z-' ]{1,20}, [A-Za-z]* ?[A-Za-z]?$";
   }
   
   public static String testDate()
   {
      //allows MM-DD-YYYY, MM/DD/YYYY, leap years and centennials accounted for, but not 400
      return "^(?!02\\-?\\/?30|02\\-?\\/?31|04\\-?\\/?31|06\\-?\\/?31|09\\-?\\/?31|11\\-?\\/?31|02\\-?\\/?29\\-?\\/?[0-9][0-9](?!04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)|02\\-?\\/?29\\-?\\/?[0-9][0-9](?!04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96))((0[1-9]|1[0-2])\\-(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\-[0-9][0-9][0-9][0-9]|(0[1-9]|1[0-2])\\/(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/[0-9][0-9][0-9][0-9])";
   }
   
   public static String testAddress()
   {
      //street number allows 0-9, 1 to 6 digits
      //optional quadrant allows caps, lower case, or logical combinations of these
      //street name allows A-Za-z0-9, 1 to 30
      //rd/st/blvd/ave type allows caps, lower case, or logical combinations of these
      return "[0-9]{1,6} (w|W|west|West|WEST|e|E|east|East|EAST|n|N|north|North|NORTH|s|S|south|South|SOUTH|nw|NW|northwest|Northwest|NorthWest|NORTHWEST|ne|NE|northeast|Northeast|NorthEast|NORTHEAST|sw|SW|southwest|Southwest|SouthWest|SOUTHWEST|se|SE|southeast|Southeast|SouthEast|SOUTHEAST) ?[A-Za-z0-9]{1,30} (road|Road|ROAD|rd|Rd|RD|street|Street|STREET|st|St|ST|boulevard|Boulevard|BOULEVARD|blvd|Blvd|BLVD|avenue|Avenue|AVENUE|ave|Ave|AVE)";
   }
   
   public static String testCityStZip()
   {
      //city allows alphabetic characters, 1 to 20
      //comma between city and state optional
      //state allows 50 states, DC, and territories
      //one or two spaces between state and zip code
      //zip code allows 5 digits, optional 4 digits for zip+4
      return "^[A-Za-z]{1,20},? (AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY|AS|DC|FM|GU|MH|MP|PW|PR|VI)\\s? [0-9]{5}(-[0-9]{4})?";
   }
   
   public static String testMilTime()
   {
      //allows HHMMSS, HH:MM:SS, range 00:00:01 to 24:00:00
      return "(?!00:00:00)(0[0-9]|1[0-9]|2[0-3]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])|(0[0-9]|1[0-9]|2[0-3])(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])(0[0-9]|1[0-9]|2[0-9])|(24:00:00|240000)";
   }
   
   public static String testUSCurrency()
   {
      //requires $, commas every 3 digits in whole dollars, two digits for cents, up to trillions
      return "^-?\\$\\d{1,3}(?:,\\d{3}){1,4}\\.\\d{2}$";
   }
   
   public static String testURL()
   {
      //requires http|HTTP|https|HTTPS, ://
      //domain name allows characters A-Za-z0-9_.-~, 1 to 30 can't start with a dash, period can't repeat
      //tld allows A-Za-z0-9, 2 to 6 characters
      return "^(http|HTTP|https|HTTPS):\\/\\/(?!-)(?!.*\\.\\.)[A-Za-z0-9_.-~]{1,30}\\.[A-Za-z0-9]{2,6}$";
   }
   
   public static String testPassword()
   {
      //requires 10, max 32 characters, at least one each of upper case, lower case, digit, punctuation mark (!#$%&'*+-/=?^_`{|}~.), no more than 3 lower case in a row
      return "(?![a-z]{3})(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!#$%&'*+-/=?^_`{|}~.])[A-Za-z0-9!#$%&'*+-/=?^_`{|}~.]{10,32}";
   }
   
   public static String testIonWords()
   {
      //allows only odd number of alphabetical characters, ending in ion, max 99
      return "([A-Za-z][A-Za-z]){1,49}(ion)$";
   }
}