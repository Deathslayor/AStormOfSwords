package net.astormofsorts.agotmod.map;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SnakePixelStream implements Iterable<SnakePixelStream.Pixel> {
    public record Pixel(int x, int y, double distance) {
    }

    // This class generates the pixels dynamically in a snake-like pattern, using a priority queue for sorting by distance
    static class SnakePixelIterator implements Iterator<Pixel> {
        private final int startX, startY;
        private final int width, height;
        private final PriorityQueue<Pixel> queue;
        private final boolean[][] visited;
        private final int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        private final int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

        public SnakePixelIterator(int width, int height, int startX, int startY) {
            this.width = width;
            this.height = height;
            this.startX = startX;
            this.startY = startY;
            this.visited = new boolean[width][height];
            this.queue = new PriorityQueue<>(Comparator.comparingDouble(p -> p.distance));

            // Initialize with the starting pixel
            queue.add(new Pixel(startX, startY, 0));
            visited[startX][startY] = true;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Pixel next() {
            if (queue.isEmpty()) {
                throw new NoSuchElementException();
            }

            Pixel current = queue.poll();

            // Add the neighboring pixels to the queue, and calculate distances
            for (int i = 0; i < 8; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isInBounds(newX, newY) && !visited[newX][newY]) {
                    double distance = Math.sqrt(Math.pow(newX - startX, 2) + Math.pow(newY - startY, 2));
                    queue.add(new Pixel(newX, newY, distance));
                    visited[newX][newY] = true;
                }
            }

            return current;
        }

        private boolean isInBounds(int x, int y) {
            return x >= 0 && x < width && y >= 0 && y < height;
        }
    }

    private final int width;
    private final int height;
    private final int startX;
    private final int startY;

    public SnakePixelStream(int width, int height, int startX, int startY) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
    }

    @Override
    public @NotNull Iterator<Pixel> iterator() {
        return new SnakePixelIterator(width, height, startX, startY);
    }

    /**
     * @param width the width of the image
     * @param height the width of the image
     * @param startX the x pos where the snake should start
     * @param startY the y pos where the snake should start
     * @return a stream of the neighbour pixels
     */
    // This method creates a dynamic stream from the iterator
    public static @NotNull Stream<Pixel> getSnakePixelStream(int width, int height, int startX, int startY) {
        SnakePixelIterator pixelIterator = new SnakePixelIterator(width, height, startX, startY);

        // Use StreamSupport to wrap the iterator in a stream
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pixelIterator, Spliterator.ORDERED), false);
    }
}