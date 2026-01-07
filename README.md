# amazing-numbers
finding properties of numbers

Amazing Numbers is a Java console application that allows users to explore various properties of natural numbers. It provides detailed information about individual numbers, lists of numbers, and allows filtering based on multiple properties.

# Features    
Get properties of a single number. List consecutive numbers with their properties. Search for numbers with specific properties or excluding certain properties.  
Validate input and handle errors gracefully. Supports properties like even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, and sad.  

# How to Use    

## Supported Requests    
Single number  
Enter a natural number to see its properties.  
Example: 7   

List of numbers  
Enter two numbers: a starting number and how many consecutive numbers to display.  
Example: 10 5 (lists 5 numbers starting from 10)  

Filtered search  
Enter a starting number, count, and properties to filter the numbers.   
Example: 1 10 EVEN -SQUARE (list 10 numbers starting from 1 that are even and not square)  

## Input Format    
Parameters are separated by spaces.  
Use a minus sign (-) before a property to exclude it.  
Enter 0 as the first parameter to exit.  

## Examples    
15 — Properties of number 15.  
20 5 — List 5 numbers starting from 20.  
1 10 EVEN -SQUARE — List 10 even numbers starting from 1 that are not square.      

# Properties List    
EVEN  
ODD  
BUZZ   
DUCK  
PALINDROMIC   
GAPFUL  
SPY  
SQUARE  
SUNNY  
JUMPING  
HAPPY  
SAD  

# Error Handling   
If invalid input is entered, the program will prompt with an appropriate message. Checks are in place to prevent mutually exclusive property requests.  
