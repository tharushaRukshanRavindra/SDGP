# importing libraries

import imp
from operator import indexOf
from sqlite3 import adapt
from xml.etree.ElementInclude import include
import numpy as np
import tensorflow as tf
import pandas as pd
import os
import cv2
import matplotlib.pyplot as pl
from tqdm import tqdm

# defining the path to the dataset
path = "ImagePro"
files = os.listdir(path)

# list and sort the files
files.sort()

# print sorted list
print(files)

# create list of image and label
image_array = []
label_array = []

# loop through each file in files
for i in tqdm(range(len(files))):
    # list of image in each folder
    sub_file = os.listdir(path + "/" + files[i])

    # check length
    # print (len(sub_file))

    # loop trhough each sub folder
    for j in range(len(sub_file)):

        #path of each image
        #example imagePro : A
        file_path = path + "/" + files[i] + "/" + sub_file[j]


        # read each image
        image = cv2.imread(file_path)

        # resize image by 96 * 96
        image = cv2.resize(image,(96, 96))

        # convert BGR image to RGB image
        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

        # add this image at image array
        image_array.append(image)

        # add label to the label_array
        # i is number from 0 to len(files) - 1
        # so we can use it as label
        label_array.append(i)

# convert list to array

image_array = np.array(image_array)
label_array = np.array(label_array, dtype="float")

# split dataset into test and train

from sklearn.model_selection import train_test_split
#output                                             train image     label      splitting size
X_train, X_test, Y_train, Y_test = train_test_split(image_array, label_array, test_size = 0.15)

# to free memory
del image_array, label_array

# to free more memory
import gc
gc.collect()

# X_train will have 85% of images
# X_test will have 15%

# create a model
from keras import layers, callbacks, utils, applications, optimizers
from keras.models import Sequential, Model, load_model

model = Sequential()
# add pretrained models to sequential
# this will use EfficientB0 pretrained model
pretrained_model = tf.keras.applications.EfficientNetB0(input_shape = (96, 96, 3), include_top = False)
model.add(pretrained_model)

# add pooling to model
model.add (layers.GlobalAveragePooling2D())

# add dropout to model
# add dropout to increase accuracy by reducing overfitting
model.add (layers.Dropout(0.3))
# finally add dense layer as an output
model.add (layers.Dense(1))

# for some tf versions reuired to build model
model.build(input_shape=(None, 96, 96, 3))

# to see model summary
model.summary()


# compile model
model.compile (optimizer = "adam", loss = "mae", metrics = ["mae"])

# create a checkpoint to save best accuracy model
ckp_path = "trained_model/model"
model_checkpoint = tf.keras.callbacks.ModelCheckpoint (
                                                        filepath = ckp_path,
                                                        monitor = "val_mae",
                                                        mode = "auto",
                                                        save_best_only = True,
                                                        save_weights_only = True
)

# monitor : monitor validation mae loss to save model
# mode : use to select save when val_mae is minimum or maximum
# it has 3 option : min, max, auto
# when val_mae reduce model will be saved
# save_best_only : False -> it will save all model
# save_weights_only : save only weight


# creating a learning rate reducer to reduce Ir when accuracy does not improve

reduce_lr = tf.keras.callbacks.ReduceLROnPlateau(
                                        factor = 0.9,
                                        monitor = "val_mae",
                                        mode = "auto",
                                        cooldown = 0,
                                        patience = 5,
                                        verbose = 1,
                                        min_lr = 1e-6
)

# factor : when it is reduce next lr will be 0.9 times of current one
# next lr = 0.9* current lr

# patience = X
# reduce lr after X epoch when accuracy does not improve
# verbose : show it after every epoch

# min_lr : minimum learnin rate

# start training

Epochs = 100
Batch_Size = 32

# X_train, X_test, Y_train, Y_test
history = model.fit(
                    X_train,
                    Y_train,
                    validation_data = (X_test, Y_test),
                    batch_size=Batch_Size,
                    epochs=Epochs,
                    callbacks=[model_checkpoint, reduce_lr]
)