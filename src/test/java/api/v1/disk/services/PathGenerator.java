package api.v1.disk.services;

import api.v1.disk.dto.Resource;

import java.util.UUID;

public class PathGenerator {

    private final String root;

    public PathGenerator(String root) {
        this.root = root;
    }

    public Resource resource(String type) {

        if ("folder".equals(type)) {
            return folder();
        }

        return file();
    }

    public Resource namedFile(String name) {
        return new Resource(name, root + "/" + name);
    }

    private Resource file() {
        String name = "file-" + UUID.randomUUID() + ".txt";
        return new Resource(name, root + "/" + name);
    }

    private Resource folder() {
        String name = "folder-" + UUID.randomUUID();
        return new Resource(name, root + "/" + name);
    }
}
