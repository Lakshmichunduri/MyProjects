import keras
from keras.layers import Activation,Dense,Dropout,Flatten,MaxPooling2D
from keras.layers.convolutional import Conv2D
from keras.models import Sequential
import warnings
import librosa
import librosa.display
import numpy as np
import pandas as pd
import random
from keras.models import model_from_json
warnings.filterwarnings('ignore')

p = []
for x in range(87):
	p.append(x)
D = []
for x in range(20):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'N_New1/New_N_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,0))
	
#print(len(D))
for x in range(20):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'AS_New1/New_AS_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,1))
#print(len(D))
for x in range(20):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'MR_New1/New_MR_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,2))
#print(len(D))
for x in range(20):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'N_New1/New_N_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,3))
#print(len(D))
for x in range(20):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'MVP_New1/New_MVP_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,4))
dataset = D
random.shuffle(dataset)
X_test,y_test = zip(*dataset)
X_test = np.array([x.reshape( (128,87,1) ) for x in X_test])
y_test = np.array(keras.utils.to_categorical(y_test,5))

# load json and create model
json_file = open('model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
# load weights into new model
loaded_model.load_weights("model.h5")
print("Loaded model from disk")
 
# evaluate loaded model on test data
loaded_model.compile(loss='categorical_crossentropy', optimizer='Adam', metrics=['accuracy'])
score = loaded_model.evaluate(X_test, y_test, verbose=0)
print("%s: %.2f%%" % (loaded_model.metrics_names[1], score[1]*100))

#predict loaded model on test data
p1 = []
for x in range(87):
	p1.append(x+13)
D1 = []
a1 = "{:03d}".format(20)
s1 = 'AS_New1/New_AS_' + a1 + '.wav'.format(20)
y, sr = librosa.load(s1,duration = 4)
ps1 = librosa.feature.melspectrogram(y=y, sr=sr)
ps11 = np.take(ps1,p1,axis =1)
X1_test = np.array([ps11.reshape( (128,87,1) ) ])
y1predicted = loaded_model.predict_classes(X1_test)
print(y1predicted)
