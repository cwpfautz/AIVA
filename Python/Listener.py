import pygame as pyg
import speech_recognition as rec
import time

recognizer = rec.Recognizer()  # initialize speech recognizer


class Listener(object):
    def __init__(self):
        print("initiating listener...")

    @staticmethod
    def tone():  # provides an audible tone to notify the user when to speak
        pyg.mixer.music.load("definite.mp3")
        pyg.mixer.music.play()
        time.sleep(1)

    @staticmethod
    def listen():  # provides a textual indicator to notify the user when to speak
        with rec.Microphone() as source:
            print("...")

            return recognizer.listen(source)

    @staticmethod
    def process(audio):  # processes audio input and returns results
        try:

            return recognizer.recognize_google(audio)
        except rec.UnknownValueError:
            print("no input detected...")

            return "error"
        except rec.RequestError as e:
            print("could not request results from Google Speech Recognition service - {0}".format(e))

            return "error"


if __name__ == "__main__":
    listener = Listener()
    listener.tone()
    print(listener.process(listener.listen()))