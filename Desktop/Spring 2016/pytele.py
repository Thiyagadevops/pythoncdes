

s=raw_input("Enter your text").upper()  #converting to upper case to avoid case difference   
while (s != 'EXIT'):               #checkpoint for exit text and recursive implementation     
  if s == '' or s == ' ':          #checkpoint for null values          
   print "Please enter some text...! you cant jus leave it empty "
   s=raw_input("Enter your text :").upper()
  else: 
   print s
   s=raw_input("Enter your text :").upper()
else: 
  quit()  
 


