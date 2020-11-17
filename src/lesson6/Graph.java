package lesson6;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public interface Graph {

    interface Vertex {
        @NotNull
        String getName();
    }

    interface Edge {
        @NotNull
        Vertex getBegin();

        @NotNull
        Vertex getEnd();

        default int getWeight() {
            return 1;
        }
    }

    @NotNull
    Set<Vertex> getVertices();

    @NotNull
    Set<Edge> getEdges();

    @Nullable
    Vertex get(String name);

    @NotNull
    default Set<Vertex> getNeighbors(@NotNull  Vertex v) {
        return getConnections(v).keySet();
    }

    @NotNull
    default int getNeighborsSize(@NotNull  Vertex v) {return getConnections(v).size();}

    @NotNull
    Map<Vertex, Edge> getConnections(@NotNull Vertex v);

    @Nullable
    default Edge getConnection(@NotNull Vertex v1, @NotNull Vertex v2) {
        return getConnections(v1).get(v2);
    }
}