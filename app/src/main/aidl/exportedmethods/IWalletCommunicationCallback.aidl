package exportedmethods;

oneway interface IWalletCommunicationCallback {

    void success();
    void failure(int failureCode);

}