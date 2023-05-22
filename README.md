# HidingInPlainText
## About
**HidingInPlaintext is a text steganography program capable of hiding ASCII text inside regular text. It uses a context-free grammar to generate text containing the secret message.**

The goal of steganography is to conceal information so that any third party is unaware of its existence.

Two grammars have been provided, one for generating a list of items with prices in .csv format, and one for generating night themed poems.

Grammars are located in the "Grammars" folder in the project directory and users can write their own grammars. Please see the example grammars.
Grammars must:
- be in greibach normal form (All non-terminals at the end of a choice).
- have no duplicate choices in a rule.
- have one rule per line.

## How it works diagram
![Encoding and decoding diagram](https://github.com/tyrellrough/HidingInPlainText/blob/main/Images/img1.png)

## Encoding example
![Encoding example image](https://github.com/tyrellrough/HidingInPlainText/blob/main/Images/img2.jpg)

## Decoding example
![Decoding example image](https://github.com/tyrellrough/HidingInPlainText/blob/main/Images/img3.jpg)
