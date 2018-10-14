import Listener
import os
import pyttsx3
import sys
import speech_recognition

voice = pyttsx3.init()
voice.setProperty('voice', voice.getProperty('voices')[1].id)
voice.setProperty('rate', voice.getProperty('rate')-35)

questions = ['What is the equation for the area of a circle?']
answers = ['Pie are squared']

voice.say('What is the equation for the area of a circle?')
answer = Listener.Listener.process(Listener.Listener.listen())
if answer == answers[0]:
    voice.say("Correct")
else:
    voice.say("Incorrect")
