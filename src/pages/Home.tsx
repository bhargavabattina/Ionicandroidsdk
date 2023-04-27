
import { IonButton, IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import ExploreContainer from '../components/ExploreContainer';
import './Home.css';

import { Plugins } from '@capacitor/core';

const { Sabpaisa } = Plugins;


function function2(){
  console.log("tada")
  console.log(Sabpaisa)

  Sabpaisa.nativeSdkCall({ firstName: "ionic",lastName: "string",email: "string@gmail.com",number: "3234323212",amount : "560" }).then((result:any) => {
    console.log(result)
});
}

const Home: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Home</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonButton onClick={() => function2()}>TestButton</IonButton>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Home</IonTitle>
          </IonToolbar>
        </IonHeader>
        <ExploreContainer name="Home page"/>
        
      </IonContent>
    </IonPage>
  );
};

export default Home;

