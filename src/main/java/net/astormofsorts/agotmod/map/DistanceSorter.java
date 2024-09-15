package net.astormofsorts.agotmod.map;

import org.jetbrains.annotations.NotNull;

import java.awt.Point;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DistanceSorter implements Iterable<Point> {

    private final Comparator<Point> distanceComparator;
    private final int startX;
    private final int startY;
    private final int width;
    private final int height;

    public DistanceSorter(Point startPoint, int width, int height) {
        this(startPoint, 0, 0, width, height);
    }

    public DistanceSorter(Point startPoint, int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.distanceComparator = (p1, p2) -> {
            double distanceSquared1 = calculateSquaredDistance(startPoint, p1);
            double distanceSquared2 = calculateSquaredDistance(startPoint, p2);
            return Double.compare(distanceSquared1, distanceSquared2);
        };
    }

    // Method to calculate the squared distance from startPoint
    private double calculateSquaredDistance(Point startPoint, Point point) {
        int dx = point.x - startPoint.x;
        int dy = point.y - startPoint.y;
        return dx * dx + dy * dy;
    }

    // Method to get an Iterable of points sorted by distance within a given area using parallel streams
    public Stream<Point> stream() {
        // Generate all points and sort them based on distance
        return IntStream.range(startX, width).parallel()  // Parallel stream for x coordinates
                .boxed()                                 // Box int to Integer to allow Stream operations
                .flatMap(x -> IntStream.range(startY, height)  // Inner stream for y coordinates
                        .mapToObj(y -> new Point(x, y)))      // Create points
                .sorted(distanceComparator);             // Sort the points
    }

    @Override
    public @NotNull Iterator<Point> iterator() {
        return stream().iterator();
    }

    public static PointList getGeneric(int width, int height) {
        return new PointList(new DistanceSorter(new Point(0, 0), - width / 2, - height / 2, width / 2, height / 2).stream().toList());
    }

    public static class PointList extends ArrayList<Point> {
        public PointList(Collection<Point> c) {
            super(c);
        }

        public PointList copyWithOffset(int x, int y) {
            return new PointList(this.stream().map(point -> new Point(point.x + x, point.y + y)).toList());
        }
    }
}
