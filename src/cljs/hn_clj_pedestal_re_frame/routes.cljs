(ns hn-clj-pedestal-re-frame.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as gevents]
   [goog.history.EventType :as EventType]
   [re-frame.core :as re-frame]
   [hn-clj-pedestal-re-frame.events :as events]))

(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (secretary/dispatch! "/new/1"))

  (defroute "/new/:page" [page]
    (re-frame/dispatch [:fetch-new-links page]))

  (defroute "/search" []
    (re-frame/dispatch [::events/set-active-panel :search-panel]))

  (defroute "/create" []
    (re-frame/dispatch [::events/set-active-panel :link-create-panel]))

  (defroute "/signup" []
    (re-frame/dispatch [::events/set-active-panel :signup-panel]))

  (defroute "/login" []
    (re-frame/dispatch [::events/set-active-panel :login-panel]))

  ;; --------------------
  (hook-browser-navigation!))
