##Introduction
 - The KWIC index system accepts:
an ordered set of lines
each line is an ordered set of words
each word is an ordered set of characters
 - Every line is "circularly shifted" and copied by:
repeatedly removing the first word
appending it at the end of the line
Outputs a listing of all circular shifts of all lines in alphabetical order

##Example
Input:		
	bar sock
	car dog	
	town fog				
Output:
	bar sock
	car dog
	dog car
	fog town
	sock bar
	town fog


##Design consideration
 - Changes in algorithm: line shifting can be performed …
on each line as it is read from the input device
on all the lines after they are read
on demand when the alphabetization requires a new set of shifted lines
 - Changes in data representation: circular shifts …
can be stored explicitly
implicitly (as index and offsets)
 - Extra Features:
Have the system eliminate circular shifts that start with certain noise words (such as "a", "an", "and", etc.).
Make the system interactive
allow the user to delete lines from original list and re-output alphabetized list
Performance: Both space and time
Reuse: To what extent can the components serve as reusable entities


## Question:
 - pipe and filter: change of data representation from string to line with index?
 - event: data sent by event?
 - blackboard: data and operation is separated?(or this problem is not so suitable for this
 style


## Thinking
 - 现在有filterA->pipe->filterB这样一个链。若filterA被替换为filterC，虽然pipe的接收格式改变了，
 但是pipe传递给filterB的格式并不改变，所以只修改pipe即可.