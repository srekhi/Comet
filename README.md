# Comet

(Note Stripe and Parse secret keys have been removed which will prevent successful user registration/ credit card details entering for anyone who uses this code... going to figure out how to have my app build successfully off of Github at a later date).

Comet is an Android app to keep people focused on their commitments. Often, I've found myself mentally commiting to doing something
(e.g running three times a week, or coding 2 hours a day) only to find myself wasting away in front of Netflix. With Comet, I've sought 
to fix this problem for myself (and hopefully many others too).

## How it works

Using Stripe's API, a user can enter his or her credit card details into the app. Then, when making a commitment, they will have the opportunity
to place a wager on that goal. For example, if they want to lift weights 4 times a week, they can wager $20 on that goal. In the app, there
is the opportunity to select a referee who will monitor this particular goal (the ref could be anyone from a spouse to a sibling to an invasive roommate).
Comet will text the referee a 5-digit code, and if the app user completes the task, the ref can give the code to the user to enter into the app by the end of the week.
If the user doesn't enter the code, they will **lose the money**. Every week, they'll have the choice to cancel the commitment or renew it.


## What's left to be done

I still need to put some finishing touches on the backend and make the UI pretty. I've begun experimenting with various color schemes but haven't
quite set on one look.

####Note:The entire commit history wasn't added to this repo, but all code is up to date.
