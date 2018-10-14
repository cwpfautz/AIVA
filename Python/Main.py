import Database
import Listener
import Parser
import pygame as pyg
import Voice

pyg.mixer.init()


class Main(object):
    def __init__(self):
        print("initiating AIVA...")
        self.file = Database.Database()
        self.audio = Listener.Listener()
        self.parser = Parser.Parser()
        self.response = Voice.Voice()

    def start(self):
        while True:
            detection = self.audio.process(self.audio.listen())
            print("Detected: " + detection)
            if detection in self.file.read("attention_keys.csv")[0]:
                self.parser.attention()
            elif any(key in detection for key in self.file.read("attention_keys.csv")[0]):
                self.parser.parse(detection)


if __name__ == "__main__":
    main = Main()
    main.start()
