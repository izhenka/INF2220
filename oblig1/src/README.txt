1. The running time analysis of your code for generating similar words:
> O(log N) * L
+ O(log N) * L * 26
+ O(log N) * L * 26
+ O(log N) * L
= 54 * L * O(log N),
hvor N - antall ord i ordboka,
L - input ord lengde

Det vil si at orden er O(log N), som kan bli til N * O(log N) hvis L er stor nok.

2. How to compile your program:
javac main/*.java

3. Which file includes the main-method
> Main.java
> ("java main.Main" for å kjøre)

4. Any assumptions you have made when implementing the assignment
-

5. Any peculiarities about your implementation
-

6. The status of your delivery (what works and what does not)
> Everything works

7. What you are most interested in receiving feedback about, and if you
want feedback in Norwegian or English (the default is Norwegian)
> Running time analysis

8. Remember to give credit
-