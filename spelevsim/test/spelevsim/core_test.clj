(ns spelevsim.core-test
  (:require [clojure.test :refer :all]
            [spelevsim.core :refer :all])
  (:import [spelevsim.core Vector3D Particle Simulation]))

(deftest test-gravitational-acceleration
  (testing "Can create gravity functions"
    (is (function? (gravitational-acceleration-function 100 100)))))

(deftest test-earth-gravity-function
  (testing "Can calculate earth gravity"
    (is (<= 9.81 (earth-gravity-acceleration 200)))
    (is (>= 9.82 (earth-gravity-acceleration 200)))))

(deftest test-Vector3D
  (testing "Can create a 3D vector"
    (let [v (Vector3D. 1 2 3)]
      (is (= (:x v) 1))
      (is (= (:y v) 2))
      (is (= (:z v) 3)))))

(deftest test-Vector3D-equal
  (testing "Equality test between Vector3D"
    (let [v1 (Vector3D. 1 2 3)
          v2 (Vector3D. 2 4 6)
          v3 (Vector3D. 1 2 3)]
      (is (Vector3D-equal v1 v3))
      (is (not (Vector3D-equal v1 v2))))))

(deftest test-Vector3D-add
  (testing "Can add 2 vectors"
    (let [v1 (Vector3D. 2 4 6)
          v2 (Vector3D. -1 -1 -1)]
      (is (Vector3D-equal (Vector3D. 1 3 5)
                          (Vector3D-add v1 v2))))))

(deftest test-Vector3D-sum
  (testing "Can sum vectors"
    (let [v1 (Vector3D. 1 2 3)
          v2 (Vector3D. 1 1 1)
          v3 (Vector3D. 1 0 0)]
      (is (Vector3D-equal (Vector3D-sum v1 v2 v3)
                          (Vector3D. 3 3 4))))))

(deftest test-Vector3D-difference
  (testing "Can subtract vectors"
    (let [v1 (Vector3D. 1 2 3)
          v2 (Vector3D. 2 3 4)]
      (is (Vector3D-equal (Vector3D-difference v1 v2)
                          (Vector3D. -1 -1 -1))))))

(deftest test-Vector3D-magnitude
  (testing "Can calculate the magnitude of a vector"
    (let [v1 (Vector3D. 3 0 4)]
      (is (= (Vector3D-magnitude v1) 5)))))

(deftest test-Vector3D-product
  (testing "Can calculate scalar product of vectors"
    (let [v1 (Vector3D. 1 2 3)
          v2 (Vector3D. 2 4 6)]
      (is (= (Vector3D-product v1 v2) 28)))))

(deftest test-Particle
  (testing "Can create a particle"
    (let [p (Particle. (Vector3D. 1 2 3)
                       (Vector3D. 1 1 1)
                       5)]
      (is (= (:pos p) (Vector3D. 1 2 3)))
      (is (= (:vel p) (Vector3D. 1 1 1)))
      (is (= (:mass p) 5)))))


(deftest test-Simulation
  (testing "Can create a simulation"
    (let [sim (Simulation. [(Particle. (Vector3D. 1 2 3)
                                       (Vector3D. -1 -1 -1)
                                       5)
                            (Particle. (Vector3D. 2 4 6)
                                       (Vector3D. 1 0 0)
                                       10)])]
      (is (= (count (:particles sim)) 2)))))
