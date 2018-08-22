package exportedmethods;

import exportedmethods.IWalletCommunicationCallback;

// Interface declaring contract between third party to this app exported methods service
interface IWalletCommunication {

    String getPublicAddress(String coinType);
    String signTransaction();
    void sendTransaction(IWalletCommunicationCallback callback);
    String getRpcAddress(String coinType);
    int getChainId(String coinType);

}