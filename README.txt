README:

The purpose of this program is to create a product where users can interact with
compositions of music.

To Run from using the command line:
  java -jar MusicEditor.jar mary-little-lamb.txt console
  java -jar MusicEditor.jar mary-little-lamb.txt midi
  java -jar MusicEditor.jar mary-little-lamb.txt gui
  java -jar MusicEditor.jar mary-little-lamb.txt composite

Functionality:
**PLEASE NOTE THAT Cuing Bar is the edge of the screen like Guitar hero**
* Press "left mouse button" where you would like to create a note.
* To create a longer note, after pressing the "left mouse button", drag the mouse to the right for
    the desired length
* Press "Right Mouse button" on a note to delete it
* Press "Middle mouse button" on a Note and Drag it to move a note.
* Press "Space Bar" to Play the Song from Where you are Currently
* Press "p" to play the song from the beginning
* Press "a" to goto customAddNoteView where you can add notes of specific Instrument and Volume
    Outside the current range and pitch of the song.
* Press "<" to Move Backwards in Song
* Press ">" to Move Forwards in Song

Changes:

  ***Changes 5/6/2016***
  There are no notable changes. More functionality was added without changing previous
  functionality in any notable way

  ***Previous Changes***
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

Questions for TA:
1.  Should we change the type T in the CompositionBuilder interface to type NoteList?
2.  If there are no notes in the list of notes, what should the console view return?
    Currently it throws an exception.
3.  Is there a specific format Desired for midi-transcript.txt