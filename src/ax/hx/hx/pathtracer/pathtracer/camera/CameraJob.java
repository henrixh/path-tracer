package ax.hx.hx.pathtracer.pathtracer.camera;

import ax.hx.hx.pathtracer.pathtracer.math.Ray;

/**
 * Created by hx on 3/25/14.
 */
public class CameraJob {
    public final int start;
    public final int end;

    public CameraJob(int start, int end) {
        this.start = start;
        this.end = end;
    }
}