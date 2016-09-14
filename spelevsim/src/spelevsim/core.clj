(ns spelevsim.core)

(defrecord Vector3D [x y z])

(defn Vector3D-equal [v1 v2]
  "Tests for vector equality"
  (and (= (:x v1) (:x v2))
       (= (:y v1) (:y v2))
       (= (:z v1) (:z v2))))

(defn Vector3D-add [v1 v2]
  "Returns vector sum of 2 vectors"
  (Vector3D. (+ (:x v1)
                (:x v2))
             (+ (:y v1)
                (:y v2))
             (+ (:z v1)
                (:z v2))))

(defrecord Particle [pos vel mass])

(defrecord Simulation [particles])
