 package ax.hx.hx.pathtracer.pathtracer;

 import ax.hx.hx.pathtracer.pathtracer.color.Influence;
 import ax.hx.hx.pathtracer.pathtracer.color.IntersectionInfo;
 import ax.hx.hx.pathtracer.pathtracer.material.DummyMaterial;
 import ax.hx.hx.pathtracer.pathtracer.math.Coordinate3;
 import ax.hx.hx.pathtracer.pathtracer.math.Normal;
 import ax.hx.hx.pathtracer.pathtracer.math.Ray;

 /**
  * Created by hx on 3/8/14.
  */
 public abstract class AbstractShape implements Shape
 {
     private static final Influence NULL_INFLUENCE = new Influence(0.0,0.0,0.0);

         private Material material = new DummyMaterial();

         /**
          * @return the material
          */
         public Material getMaterial() {
                 return material;
         }

         /**
          * @param material the material to set
          */
         public void setMaterial(Material material) {
                 this.material = material;
         }



     public Influence traceLastHit(int depth, Scene scene, IntersectionInfo hit){
         // 1. We ask our material about a new outgoing ray (Which can be null)
         // 2. If we got a new outgoing ray, we trace it,
         //    otherwise set incomingInfluence to NULL_INFLUENCE.
         // 3. Throw the information at the material and see what we get.

         // 1. Ask the material about a new ray.
         Ray newRay = material.getRandomRay(hit.incoming, hit.normal, hit.hitCoord);

         // 2. Get some incomingInfluence
         Influence incomingInfluence;
         if (newRay == null){
             incomingInfluence = NULL_INFLUENCE;
         }


	 // Here is the place for the big recursive happening!! :D
         else {
             incomingInfluence = scene.pathtrace(newRay, depth);
         }

         // 3. Ask the material

         // Please observe, the material is strange, so while the rest
         // of the path tracing code refers to rays like something
         // going OUT of the camera and IN to the surface, the
         // material deals with this the other way, the incoming Ray
         // is the ray with the origin at the observer, and the
         // outgoing ray is the ray with the origin at the shapes surface.
         return material.calculateInfluence(newRay, hit.incoming, hit.normal, incomingInfluence);


     }
 }
