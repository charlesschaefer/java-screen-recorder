#!/bin/bash
cd files
avconv -r 10 -start_number 1 -i screen-%d.jpeg -b:v 1000k ../video.mp4
