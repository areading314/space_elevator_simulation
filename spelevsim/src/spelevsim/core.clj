(ns spelevsim.core
  (:require [clojure.math.numeric-tower :as math]))

(defrecord Vector3D [x y z])

(def gravitational-constant 6.67384e-11)
(def earth-mass 5.9722e24)
(def earth-radius 6.371e6)

(defn gravitational-acceleration-function [radius mass]
  (fn [height] (/ (* gravitational-constant mass)
                  (math/expt (+ radius height) 2))))

(def earth-gravity-acceleration
  (gravitational-acceleration-function 6.371e6 5.9722e24))

(defn Vector3D-equal [v1 v2]
  "Tests for vector equality"
  (and (= (:x v1) (:x v2))
       (= (:y v1) (:y v2))
       (= (:z v1) (:z v2))))

(defn Vector3D-add [v1 v2]
  "Returns vector sum of 2 vectors"
  (Vector3D. (+ (:x v1) (:x v2))
             (+ (:y v1) (:y v2))
             (+ (:z v1) (:z v2))))

(defn Vector3D-sum [& vs]
  "Returns the sum of vectors in vs"
  (reduce Vector3D-add vs))

(defn Vector3D-difference [v1 v2]
  "Returns the difference of 2 vectors"
  (Vector3D. (- (:x v1) (:x v2))
             (- (:y v1) (:y v2))
             (- (:z v1) (:z v2))))

(defn Vector3D-magnitude [v]
  "Returns the magnitude of a vector"
  (math/sqrt (Vector3D-product v v)))

(defn Vector3D-product [v1 v2]
  (reduce + [(* (:x v1) (:x v2))
             (* (:y v1) (:y v2))
             (* (:z v1) (:z v2))]))

(defrecord Particle [pos vel mass])

(defrecord Simulation [particles])
