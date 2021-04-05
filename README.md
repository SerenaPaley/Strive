# Strive
*Strive* is a Java made productivity application made for reaching your goals!

### Usage
 *Strive* allows you to set and organize your goals into daily, weekly, monthly and year long goals. Attach a number of stars to each goal as motivation and to set priority.
***
**Features**:
- Add/remove goals from your agenda
- Check off completed goals
- Assign stars to goals based on priority
- Track how many goals you have achieved
- Print a categorized list of goals


**Ideal user**:  
*Strive* can be used for a variety of goal and motivational purposes. Users will be people who want to achieve their goals, motivate themselves with chosen rewards, and track their progress in one convenient place.
***
### Motivation Behind Strive
*Strive* was created to be a space to organize all of my goals and tasks. Being able to visually see previously completed goals is very motivating and allows the user to set SMART goals with reasonable timelines.
***
### Roadmap
In the future, *Strive* will include a rewards system where users can set their own rewards and choose a number of stars to unlock it at.  

**Including**:  
- Visual rewards bar  
- Personalized rewards
- Countdown of stars needed for next reward
***

### User Stories   
 
- As a user, I want to be able to add goals to my agenda
- As a user, I want to be able to remove goals from my agenda
- As a user, I want to be able to update existing goals
- As a user, I want to be able to mark a goal as completed
- As a user, I want to be able to view a list of goals separated into daily, weekly, monthly, yearly, and completed goals.
- As a user, I want to be able to save my agenda filled with goals to a file
- As a user,  I want to have the option to load a saved agenda from a file when the application starts

### Phase 4: Task 2
I chose to complete option 1: Test and design a class in your model package that is robust <br>
I made my Agenda class robust by introducing a checked exception called EmptyGoalListException <br>
The methods removeGoal() and updateGoal() throw EmptyGoalListException and no  longer have a requires clause