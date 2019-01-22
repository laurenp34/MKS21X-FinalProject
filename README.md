# MKS21X-FinalProject
TO RUN PROGRAM
compile: `javac -cp gson-2.8.5.jar:lanterna.jar:.`
run: `java -cp gson-2.8.5.jar:lanterna.jar:.`
1. input a book title
2. select which of the returned results is preferred
3. a list of copies with availability status and location within the NYPL system will appear.
4. Follow instructions within the program for viewing map

**Useful link: ascii-table.com/ansi-escape-sequences-vt-100.php

# Daily Logs
*3 January 2019*\
Kiran-Familiarized myself with Google GSON library and built rudimentary code for inputting a search term and receiving relevant data from OpenLibrary\
Lauren-Downloaded Lanterna and Mr. K's TerminalDemo. Figured out differences between Windows and the computers at school for Lanterna (-cp is different). Looked into TextBox as a possible class for inputting the search term.

*4 January 2019*\
Kiran-Began exploration of XML parsing in Java, as well as the structure of NYPL's catalog and how it would be necessary to search. Set up structural classes and interfaces in preparation for web scraping code.\
Lauren-was sick :( 

*5 January 2019*\
Kiran-Clarified and simplified code from JSON parsing of OpenLibrary with comments and proper Reader methods rather than online example code.\
Lauren-Created Search program which generates a search message at the top of the terminal and stores the input as a string. Also worked on creating a class structure (because I originally wrote everything in the main). 

*6 January 2019*\
Kiran-Encountered the issue of XML parser timing out during the process of reading an HTML file as large as the one present in the NYPL's catalog. Explored several libraries and methods of XML parsing to determine how to proceeed properly.\
Lauren-Worked on fixing the usage of the program. For example, clearing the terminal screen and exiting the program. Also familiarized myself with TerminalScreen in Lanterna.

*7 January 2019*\
Kiran-Continued experimentation with different methods to change XML parsing mechanism in order to avoid issue of too large files.\
Lauren-Merged my search branch with master, which meant merging the two Search.java files. So far, my Search.java was focused on retrieving user input, whereas Kiran's was focused on using a given String (search term) and searching through the json file for matches. I combined our two files and wrote a method that prints out the results of the search, by passing the string generated from user input into Kiran's search methods. (the method doesn't work 100% yet)

*8 January 2019*\
Kiran-Successfully wrote method to access only the blocks of code containing the relevant data from the NYPL's catalog search. Though the program still runs relatively slowly, it is now able to successfully retreive data, in the form of HTML only including the necessary sections.\
Lauren-Successfully modified the searchTerm instance variable and buildSearch() in the object directly rather than thorugh a static method. At the end, was debugging in order to figure out how the data is stored in the instance variables. Will continue looking at the json file and how it is transferred into isntance variables, because the next step is creating a formatted list of search results.

*9 January 2019*\
Kiran-Initially wrote code using the built-in XML DOM parser to access data within the snippets of code previously created. Upon successful parsing, I discovered that the static download of the webpage doesn't include all necessary data, as the browser webpage updates after the initial load filling in data. Changed course to only extract ID data so that pages containing full data can be individually accessed in future.\
Lauren-At first, tried to change the search input from String to ArrayList<Character> to aid in backspace (removing) elements -- however, this didn't work and somehow messed up many other things so I tried to recover the older (previous) commit. I was in a detached head state. Then I worked on allowing the user to choose the search result. I numbered the results, then gave the user a space to input the corresponding number for a result. I also created a checker that ensured that the input was a integer in range, and if not then it would retry the search. At the end when I pushed my changes I just pushed the head state from before and lost all my commits and work! Will try and recover tomorrow (because I tried on my own and couldn't come up with anything).\
  
*10 January 2019*\
Kiran-Wrote the beginnings of code to access multiple pages out of the search result, and gained a better understanding of how the w3c DOM parsing library works.\
Lauren-Tried to recreate what I did on 1/9, which was erased. Got up to date and created roughly what I ended up with at the end of the night yesterday. However, at the end of the night it was erased again! It was because when I tried to git push, git rejected this, saying that my branch Search was behind and I needed to git pull first. But when I did that, my work was erased. That's why there are no commits for 1/10 and 1/9. Will work on figuring out why and restoring the work.

*11 January 2019*\
Kiran-Quiz day, little work accomplished.\
Lauren- same ^

*12 January 2019*\
Kiran-Successfully completed the code for accessing the NYPL webpage and all of its data, and downloading it into the form of Copy objects\
Lauren-Went to StuyHacks and ended up working on a different project instead (sorry :( ) but read up on Github and how branches work to avoid the same problem as the previous two work days.

*13 January 2019*\
Kiran-Improved the printing of copy data downloaded for purposes of demo. Merged code into master alongside Lauren's code and resovled conflicts.\
Lauren-Recreated lost code (choosing search result and ensuring the input is a valid integer within range) , this time using Scanner rather than by reading lanterna key inputs, which is much simpler. Also modified runSearch() to return a Book object so that it is compatible with Kiran's webscraping program. Also added code to re-initiate the search when a search term yields 0 books, and formatted print statements for Demo.

*14 January 2019*\
Kiran-Intense period cramps meant I didn't work.\
Lauren-Familiarized myself with ANSI escape code, and used it to show the text as it's being inputted (rather than before when you couldn't see each character as you typed). Also looked at example code for creating a progress bar using ANSI, which would be useful for the library data loading into the calendar, or fetching the availability results from NYPL. 

*15 January 2019*\
Kiran-Found the json file in which library location data for the NYPL is stored, and wrote a file to parse said JSON file and turn it into our own Branch objects, which I then saved in an external file for quick access during a program run. Wrote a static method that takes an unchanging JSON file and generates a Branch array, for use in the rest of the program. Restructured Copy construction to use the now built Branch objects (required manual modification of some NYPL system IDs to match their own code, obnoxiously). Merged all of this in. \
Lauren-Used ANSI to show and hide the cursor. Also familiarized myself with Kiran's code that she added today^^. Tried to use storedCopies (AL in Branch) to generate another list of branches with the copy available, however storedCopies kept turning up null. Spent a long time trying to figure out why, and wrote many debug parts, but ultimately couldn't get anywhere.

*16 January 2019*\
Kiran-Corrected issues in yesterdays code, reformatted Driver to display copies sorted by branch.(With Lauren, in person) Researched the structure of a KML file and how the corresponding DOM Document would need to be built. Using this information, wrote the Map object, which in its construction takes a series of Branches and makes them Placemarks in a KML DOM Document which is in turn written to an external file. Succeeded at displaying this file in an online KML file viewer. (Google MyMaps)\
Lauren-Began by creating updateDueDMY method which parses the status of each unavailable book into int day,mon,yr. Then used this to update the dueDate of the Copy object. Along the way, created Date class and the get and set methods for it. In Driver, created a very basic Date[][] calendar, however even though Date() was initialized it threw NullPointerExceptions. Will continue debugging and testing these new methods tomorrow.

*17 January 2019*\
Kiran-Completed and corrected errors in the toFile method for Map. Began testing the contents of the map on various books, with success. Began research into how the Style blocks of KML files work to be able to distinguish between locations where all books are checked out and where there are currently available clopies.\
Lauren-Kiran helped me fix the errors I couldn't fix last night, so Date now has a working storedCopies array (problem was with loop logic). Didn't accomplish much else because I had so much work from other classes, but I did read up on Calendar APIs and creating a plaintext calendar or some external software. Looked into GregorianCalendar which can provide the current day and time, which will be useful for the calendar. Will look more into it tomorrow and discuss with Kiran whether the calendar will be plaintext or not, and then begin writing code.

*18 January 2019*\
Kiran-In class tested the capability of school computers to load KML files (and approved the somewhat roundabout way of displaying the files by Mr. K) Got home late so I didn't work at home.
Lauren-In class Mr. K helped me fix method in Copy.java by adding an import statement. Other than that no work was done because of StuySquad and road trip.
 
 *19 January 2019*\
 Kiran-Made extensive efforts to learn how to style a KML file in a way that the style would appear on Google MyMaps. Despite the fact that I was able to build code that was able to build a Style block, nothing changed when I attempted to view the file online. After attempting to exactly replicate the style blocks of KML files exported from MyMaps, still with no success, I ultimately gave up on styling the map.\
 Lauren-Updated Date.java and Calendar.java to have different manes (different from Java built in objects). Began the calendar-making process by including in Driver code to find the start and end months the calendar should include. Created new Month object - stores a MyDate[] - to be used in calendar. Sucessfully made constructor for this new object that initializes array w correct number of days and correct days. Update updateDueDateDMY() to return Date -to be used in finding first and last month. Also fixed errors in getFirstDateOfMonth().
 
 *20 January 2019*\
 Kiran-Took a day off to work on other final projects.\
 Lauren-Began process of creating calendar. Finished debugging date and updateDueDate(), so I scrapped  the previous testing calendar to create one based on real data from books. Also used LocalDateTime object to find current day and month. So now a dynamic LibraryCalendar can be created given real book data. Began formatting calendar for better printing. 
 
 *21 January 2019*\
 Kiran-Created some last minute final touches to make the Map display clearer (most notably, changed the format to show a count of available books rather than the toString of each individual copy). Additionally, clarified instructions for using the KML file and merged the Map branch back into master.\
 Lauren-Created input options for viewing the NYPL results. created counters for different attributes to print at the top of the results. Finalized formatting for everyting (new pages, new lines, error stmts, removing debug stmts, etc.). Also migrated everything in Driver to static methods to look cleaner. In calendar, made sure that it doesn't print past today (doesn't print days from the past). To do so I created other constructors in Month and LibraryCalendar. Merged with Kiran, added her map code to another option in InputHandler. Everything works!!
