import Database
import Listener
import os
import pyttsx3
import random
import sys
from weather import Weather
import webbrowser as web

voice = pyttsx3.init()
voice.setProperty('voice', voice.getProperty('voices')[1].id)
voice.setProperty('rate', voice.getProperty('rate') - 35)

weather = Weather()


class Parser(object):
    def __init__(self):
        print("initiating parser...")
        self.file = Database.Database()
        self.audio = Listener.Listener()

    def open(self, detection):
        voice.say(random.choice(self.file.read("confirm_resp.csv")[0]))
        voice.runAndWait()
        if any(key in detection for key in self.file.read("chrome_keys.csv")[0]):
            web.open("https://www.google.com")
        elif any(key in detection for key in self.file.read("email_keys.csv")[0]):
            web.open("https://www.gmail.com")
        elif any(key in detection for key in self.file.read("netflix_keys.csv")[0]):
            web.open("https://www.netflix.com")
        elif any(key in detection for key in self.file.read("youtube_keys.csv")[0]):
            web.open("https://www.youtube.com")
        elif any(key in detection for key in self.file.read("file_keys.csv")[0]):
            os.system("cmd /c start explorer.exe")
        elif any(key in detection for key in self.file.read("desktops_keys.csv")[0]):
            os.popen("cmd /c start Desktop")
        elif any(key in detection for key in self.file.read("downloads_keys.csv")[0]):
            os.system("cmd /c start Downloads")
        elif any(key in detection for key in self.file.read("documents_keys.csv")[0]):
            os.system("cmd /c start Documents")
        elif any(key in detection for key in self.file.read("pictures_keys.csv")[0]):
            os.system("cmd /c start Pictures")
        elif any(key in detection for key in self.file.read("settings_keys.csv")[0]):
            os.system("cmd /c start ms-settings:")

    def close(self, detection):
        voice.say(random.choice(self.file.read("confirm_resp.csv")[0]))
        voice.runAndWait()
        if any(key in detection for key in self.file.read("chrome_keys.csv")[0]):
            os.system("TASKKILL /F /IM chrome.exe")

    def parse(self, detection):
        if any(key in detection for key in self.file.read("whoat_keys.csv")[0]) and any(key in detection for key in
                                                                                        self.file.read(
                                                                                            "aiva_keys.csv")[0]):
            voice.say(random.choice(self.file.read("aiva_resp.csv")[0]))
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("whoat_keys.csv")[0]) and any(key in detection for key in
                                                                                          self.file.read(
                                                                                              "aivaname_keys.csv")[0]):
            voice.say(random.choice(self.file.read("aivaname_resp.csv")[0]))
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("condition_keys.csv")[0]):
            voice.say(random.choice(self.file.read("condition_resp.csv")[0]))
            voice.say(random.choice(["Thanks for asking.", ""]))
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("terminate_keys.csv")[0]):
            print("terminating systems...")
            voice.say(random.choice(self.file.read("terminate_resp.csv")[0]))
            voice.say("Goodbye.")
            voice.runAndWait()
            sys.exit()
        elif any(key in detection for key in self.file.read("whoat_keys.csv")[0]) and (any(key in detection for key in
                                                                                           self.file.read(
                                                                                               "user_keys.csv")[0]) or
                                                                                       (any(key in detection for key in
                                                                                            self.file.read(
                                                                                                "username_keys.csv")[
                                                                                                0]))):
            voice.say(random.choice(self.file.read("user_resp.csv")[0]))
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("change_keys.csv")[0]) and any(key in detection for key in
                                                                                           self.file.read(
                                                                                               "username_keys.csv")[0]):
            if "to " in detection:
                self.file.write("user_name.csv", detection[detection.find("to")+3])
                voice.say(random.choice([self.file.read("confirm_resp.csv")[0]]))
                voice.runAndWait()
            else:
                voice.say(random.choice(self.file.read("changeuser_resp.csv")[0]))
                voice.runAndWait()
                self.audio.tone()
        elif any(key in detection for key in self.file.read("thank_keys.csv")[0]):
            voice.say(random.choice(self.file.read("welcome_resp.csv")[0]))
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("whatup_keys.csv")[0]):
            voice.say(random.choice(self.file.read("whatup_resp.csv")[0]))
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("open_keys.csv")[0]):
            self.open(detection)
        elif any(key in detection for key in self.file.read("close_keys.csv")[0]):
            self.close(detection)
        elif any(key in detection for key in self.file.read("search_keys.csv")[0]):
            for key in self.file.read("attention_keys.csv")[0]:
                if key in detection:
                    detection = detection.replace(key, '', 1)
            for key in self.file.read("search_keys.csv")[0]:
                if key in detection:
                    detection = detection.replace(key, '', 1)
            detection = detection.replace(' ', '', 1)
            web.open("https://www.google.com/search?q=" + detection)
            voice.say(random.choice(["Here's what I found.", "Searching for " + detection, "Here's what I found for " +
                                     detection]))
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("weather_keys.csv")[0]) and "tomorrow" in detection:
            location = weather.lookup_by_location('Granville')
            forecasts = location.forecast()
            voice.say("Tomorrow will be " + forecasts[1].text() + ", with a high of " + forecasts[1].high() +
                      " degrees and a low of " + forecasts[1].low() + "degrees celsius.")
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("weather_keys.csv")[0]):
            location = weather.lookup_by_location('Granville')
            forecasts = location.forecast()
            voice.say("Today's forecast is " + forecasts[0].text() + ", with a high of " + forecasts[0].high() +
                      " degrees and a low of " + forecasts[0].low() + " degrees celsius.")
            voice.runAndWait()
        elif any(key in detection for key in self.file.read("greeting_keys.csv")[0]):
            voice.say(random.choice(self.file.read("greeting_resp.csv")[0]))
            voice.runAndWait()
        elif any(key in detection for key in "Quiz me"):
            voice.say("What is the equation for the area of a circle?")
            voice.runAndWait()
            detection = self.audio.process(self.audio.listen())
            if any(key in detection for key in "Pie are squared"):
                voice.say("Correct")
                voice.runAndWait()
            else:
                voice.say("Incorrect")
                voice.runAndWait()

    def attention(self):
        self.audio.tone()
        detection = self.audio.process(self.audio.listen())
        '''
        voice.say(random.choice(self.file.read("attention_resp.csv")[0]))
        voice.runAndWait()
        '''
        print("Detected: " + detection)
        self.parse(detection)


if __name__ == "__main__":
    parser = Parser()
    parser.parse(input(">>> "))
