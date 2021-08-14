# WhatIsMyAge

## Problem
Predict someone's age based on their first name

## Background
This program stores Social Security Administration data on name frequency and accepts user queries. 
Files included are:
- **Data Strucures & Tests:** ArrayList, ArrayListTest, Iterator, LinkedList, LinkedListTest, Node
- **Objects classes for organizing data:** Query, Record
- **Other classes:** AgePredictor, CommandLineInterface, Configuration

# Challenges
I broke this project into smaller pieces by creating many classes. However, the overall design is clunky.
If I had more timem I would have rewrote the classes that do the most heavy lifting in this program:
AgePredictor, CommandLineInterface, and Configuration.

# Complexity
Initially, my LinkedList implementation was slow because I did not implement a tail pointer. This mean that the add()
function was O(n). I modified LL to include a tail pointer so that add() could be O(1).
Overall, creating each list is O(n) and querying each list is O(n) so the overall program is O(n).
