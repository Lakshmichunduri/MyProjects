
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
warnings.filterwarnings('ignore')
p = []
for x in range(87):
	p.append(x)
D = []
for x in range(200):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'N_New1/New_N_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,0))
	

for x in range(200):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'AS_New1/New_AS_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,1))

for x in range(200):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'MR_New1/New_MR_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,2))

for x in range(200):
	x = x + 1
	a = "{:03d}".format(x)
	s = 'MS_New1/New_MS_' + a + '.wav'.format(x)
	y, sr = librosa.load(s,duration = 4)
	ps = librosa.feature.melspectrogram(y=y, sr=sr)
	shape = ps.shape
	if shape[1] < 87 : continue
	ps1 = np.take(ps,p,axis =1)
	D.append((ps1,3))

for x in range(200):
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
train = dataset[:900]
test = dataset[900:]

X_train, y_train = zip(*train)
X_test,y_test = zip(*test)

X_train = np.array([x.reshape( (128,87,1) ) for x in X_train])
X_test = np.array([x.reshape( (128,87,1) ) for x in X_test])

y_train = np.array(keras.utils.to_categorical(y_train,5))
y_test = np.array(keras.utils.to_categorical(y_test,5))

#sequential model
model = Sequential()
model.add(Conv2D(32, (5,5) , input_shape = (128,87,1)))
model.add(MaxPooling2D((2,2), strides = (2,2)))
model.add(Activation('relu'))

model.add(Conv2D(48, (5,5), padding = "valid"))
model.add(MaxPooling2D((2,2),strides =(2,2)))
model.add(Activation('relu'))

model.add(Conv2D(48, (5,5), padding = "valid"))
model.add(Activation('relu'))

model.add(Flatten())
model.add(Dropout(rate = 0.5))

model.add(Dense(64))
model.add(Activation('relu'))
model.add(Dropout(rate = 0.5))

model.add(Dense(5))
model.add(Activation('softmax'))

#compiling the model
model.compile(optimizer = "Adam",loss = "categorical_crossentropy",  metrics = ['accuracy'])

#fitting into model
model.fit(
	x =X_train,
	y =y_train,
	epochs=12,
	batch_size = 128,
	validation_data =(X_test ,y_test))

score = model.evaluate(
	x = X_test,
	y = y_test)

print('Test loss:', score[0])
print('Test accuracy:', score[1])

#saving the model into json file
model_json = model.to_json()
with open("model.json", "w") as json_file:
    json_file.write(model_json)
# serialize weights to HDF5
model.save_weights("model.h5")
print("Saved model to disk")




