(ns spelevsim.core-test
  (:require [clojure.test :refer :all]
            [spelevsim.core :refer :all])
  (:import [spelevsim.core Vector3D Particle Simulation]))

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
