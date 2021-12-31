# PhotoChallengeApp
Coding challenge application



Things I solved well in my opinion:

- dependency injection with Koin
- readability of code, separation of tasks according to MVVM



Things I would improve if I had more time:

- tests implementations (I didn't manage to write them within the time limit, most would probably be Integration tests with Android components in case of that application, but I've started to learn and write these with Koin)
- I would implement a database that holds retrieved data from API, rather than download them every time
- there was a problem with urls of pictures in the API (both when using Glide and Picasso), some will be working when converted to .png, those that aren't are replaced with placeholder - given more time, I would write a function that tries converting them to .png or .jpeg and probably most, if not all would work
