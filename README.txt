README:

The purpose of this program is to create a product where users can interact with compositions of music.

Changes:

  The Note class was changed to accomodate fields necessary for the MIDI view.
  The getter-setter pattern was also applied here.

  The NoteList class was changed to accomodate that the given txt files'
  definition of the end of a note. The original model assumed that the 
  end time was the last timestamp the note would play, but the txt file
  assumes that the end time is the first timestamp where the note doesn't
  play. The add, delete, and contains method with accompanying unit tests
  were changed to accomodate this. 
 
  The NoteList class was also changed so that if a user tried to get all notes 
  at a certain timestamp and that timestamp did not happen to be recorded in 
  the model, the model would not throw an error saying "invalid timestamp." 
  Instead, an empty list is returned. This is safe because there is no modification
  of the list anywhere in the view code; the view code merely reads the 
  contents of the list. 

  All three views are connected in that they implement a View interface. 

  In the future, we plan to change the data structure behind the NoteList class
  so that instead of it being a TreeMap<Integer, HashSet<Note>>, it will be a
  HashSet<Integer, TreeSet<Note>>. This will eliminate a lot of code in the 
  view that makes the whole program slower - the view is currently trying to 
  compensate for the fact that HashSets' elements are not ordered from least
  to greatest.   

TODO:

1.  unite the MIDI aspects of the note with the note itself,
    and write any additional tests to cover
2.  write tests for any methods added to the note class,
    and compensate for the fact that we now allow overlap of notes
3.  add why we make changes to the note class to readme.
4.  reorganize the notelist class and write javadoc for it in the
    interface and make tests for it.
5.  add why we made changes to notelist class in the readme. 
6.  cut down giant methods in the view to make them smaller,
    especially if the same exact thing is copied into multiple places
7.  abstract common functionality to the interface level.
    record what functionalities were abstracted in the readme.
9.  polish and sharpen layout of GUI (maybe?)
10. make a builder class inside of noteList class. we need 
    this in order to fix the errors you encountered in util.
11. fix all files in util to correspond to the builder class 
    in noteList
12. clean up main so that it only calls one thing -there 
    should be no leaking of implementation in main 
    (if it wasn't done already)
13. Complete the main() method, so that running your program 
    with arguments "mary.txt" and "console" will produce the 
    text output above, and running it will arguments 
    "mystery-1.txt" and "midi" will play the first mystery 
    file via MIDI. This wil be the final version of the main 
    method that we submit.
14. update the readme to include general design concepts 
    incorporated into the view
15. Submit a file named console-transcript.txt, showing the 
    output of the console view when rendering the 
    mystery-1.txt file.
16. Submit a file named midi-transcript.txt, showing the 
    mocked transcript output of the MIDI view when rendering mary.txt.
17. Submit a screenshot of your GUI view, rendering the 
    mystery-2.txt file. You must submit it as a JPEG, with 
    file extension .jpg, or as a PNG, with file extension .png 
    — and the file extensions are case-sensitive.
18. make a jar file
19. Consider adding a MessageCloseFunction (Written by Justin)
20. Make sure EVERYTHING has Public Constructors (Justin)
21. Give View things to have??? (Justin)


Questions for TA:
1.  Should we change the type T in the CompositionBuilder interface to type NoteList?
2.  If there are no notes in the list of notes, what should the console view return?
    Currently it throws an exception.
3.  Is there a specific format Desired for midi-transcript.txt
