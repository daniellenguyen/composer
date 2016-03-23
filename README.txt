README:

The purpose of this program is to create a product where users can interact with compositions of music.

Changes:TODO: put all changes here

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
8.  make a mock MIDI to test midi. instructions for this are
    near the bottom of the assignment page.
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


Questions for TA:
1.  Should we change the type T in the CompositionBuilder interface to type NoteList?
2.  If there are no notes in the list of notes, what should the console view return?
    Currently it throws an exception.
