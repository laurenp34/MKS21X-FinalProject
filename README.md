# MKS21X-FinalProject
note: when running, the necessary classpath is
`-cp gson-2.8.5.jar\;lanterna.jar\;.`
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
Kiran-
Lauren-At first, tried to change the search input from String to ArrayList<Character> to aid in backspace (removing) elements -- however, this didn't work and somehow messed up many other things so I tried to recover the older (previous) commit. I was in a detached head state. Then I worked on allowing the user to choose the search result. I numbered the results, then gave the user a space to input the corresponding number for a result. I also created a checker that ensured that the input was a integer in range, and if not then it would retry the search. At the end when I pushed my changes I just pushed the head state from before and lost all my commits and work! Will try and recover tomorrow (because I tried on my own and couldn't come up with anything).
