#!/bin/bash

CONTAINER_ID=$(docker ps -a --filter "ancestor=sudoku:0.0.1" --format "{{.ID}}")

if [ -n "$CONTAINER_ID" ]; then
    echo "Stopping container $CONTAINER_ID..."
    docker stop "$CONTAINER_ID"

    echo "Removing container $CONTAINER_ID..."
    docker rm "$CONTAINER_ID"
else
    echo "No container found with image 'sudoku:0.0.1'."
fi